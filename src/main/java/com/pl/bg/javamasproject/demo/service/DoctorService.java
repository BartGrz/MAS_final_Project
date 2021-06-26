package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.*;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final static Logger logger = LoggerFactory.getLogger(DoctorService.class);

    private DoctorRepository doctorRepository;
    private SpecializationRepository specializationRepository;
    private DoctorSpecRepository doctorSpecRepository;
    private VisitRepository visitRepository;

    @Getter
    private boolean booked = false;

    public DoctorService(DoctorRepository doctorRepository, SpecializationRepository specializationRepository, DoctorSpecRepository doctorSpecRepository, VisitRepository visitRepository) {
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
        this.doctorSpecRepository = doctorSpecRepository;
        this.visitRepository = visitRepository;
    }

    /**
     * adding given specialization to doctor list of specializations - if specialization have not been added before , method will add it to collection in class and to the DoctorSpec class saving it to database
     *
     * @param id_doctor
     * @param toAdd
     */
    public void addSpecialization(int id_doctor, Enum<Specialization.SpecializationTypes> toAdd) {


        int id_spec = specializationRepository.findByName(toAdd.toString());
        var doctor = doctorRepository.findById(id_doctor).get();
        var specializations = getDoctorsSpecializations(doctor);

        if (!specializations.stream().anyMatch(specialization -> specialization.getName().equals(toAdd.toString()))) {
            doctor.getDoctorSpecializations().add(toAdd.toString());
            doctorSpecRepository.saveSpecialization(doctor.getId_doctor(), id_spec);
            logger.info("specialization " + toAdd + " added to " + doctor.getId_doctor());

        } else {
            logger.info("specialization " + toAdd + " has been already added to " + doctor.getId_doctor());
        }
    }

    public Set<Specialization> getDoctorsSpecializations(Doctor doctor) {
        return doctor.getDoctorSpecs().stream().map(DoctorSpec::getSpecialization).collect(Collectors.toSet());
    }

    /**
     * By this method all possibles hours for visit are shown accessed via DoctorOfficeHours field in Doctor class .
     * @param doctor
     * @param date_of_visit
     * @return list of doctors office hour which are not assaignet to any visit
     * @throws ParseException
     */
    public List<LocalTime> getDoctorsHours(Doctor doctor, LocalDate date_of_visit) throws ParseException {

        var officeHours = doctor.getDoctorOfficeHours().getOfficeHours();
        var ld = LocalDate.parse(date_of_visit.toString());
        int day = ld.getDayOfWeek().getValue();
        String beginning_hour;
        /*
        Getting doctor's office hours based on day of the week (1:monday, 7:sunday)
         */
        switch (day) {
            case 1:
                beginning_hour = officeHours.getMonday();
                break;
            case 2:
                beginning_hour = officeHours.getTuesday();
                break;
            case 3:
                beginning_hour = officeHours.getWednesday();
                break;
            case 4:
                beginning_hour = officeHours.getThursday();
                break;
            case 5:
                beginning_hour = officeHours.getFriday();
                break;
            case 6:
                beginning_hour = officeHours.getSaturday();
                break;
            case 7:
                beginning_hour = officeHours.getSunday();
                break;
            default:
                beginning_hour = "not working";
                break;
        }

        //if doctor is not working at this particular day, it will return empty collection

        List<LocalTime> listOfHours = new ArrayList<>();
        if (beginning_hour.equals("not working")) {
            return listOfHours;
        }
        /*
        else it will proceed to find beginning hour and ending hour from String type by split method,
         then parsing first element of array as a beginning hour, second as ending and based on it i create loop
         (how many hour is doctor woking this day : ex. 10:30 -18:30 = 8;
         */
        String[] tab = beginning_hour.split(" - ");
        LocalTime time = LocalTime.parse(tab[0]);
        LocalTime endTime = LocalTime.parse(tab[1]);
        int length = endTime.getHour() - time.getHour();

        for (int i = 0; i < length; i++) {
            listOfHours.add(time);
            time = LocalTime.of(time.getHour() + 1, time.getMinute());
        }

        /*
        check if doctor has any visit already assaigned for this date, if has -> it will compare hours of doctors office to its assaigned visits begin hours
        when compareTo method returns 0 it means that this hour is booked
         */

        if (!doctor.getVisits().isEmpty()) {
            if (doctor.getVisits().stream().map(Visit::getDate_of_visit).findAny().get().compareTo(date_of_visit) == 0) {

                for (Visit visit : doctor.getVisits()) {
                    var hour = visit.getBeginning_time();
                    listOfHours.removeIf(localTime -> {
                        if (localTime.compareTo(hour) == 0) {
                            return true;
                        }
                        return false;
                    });
                }
                booked=true;
            }
        }
        return listOfHours;
    }
    /**
     * if nbew doctor is created it shoudl be automatically assaigned to office
     * @param doctor
     * @param office
     */
    public void createNewDoctor (Doctor doctor, Office office) {

        if (!doctorRepository.existsById(doctor.getId_doctor())) {
            doctor.setOffice(office);
            office.setDoctor(doctor);
            doctorRepository.save(doctor);
            doctorRepository.insertIntoOffice(doctor.getId_doctor(),office.getId_office());
            logger.info("Doctor saved to database");
        }

    }
    /**
     * removing doctor indicates removing references
     * @param doctor
     */
    public void removeDoctor(Doctor doctor) {
        //if doctor exist then
        if(doctorRepository.existsById(doctor.getId_doctor())) {
           var visit = doctor.getVisits();
           //for any visit doctor was assaigned to :
           visit.forEach(vis -> {
               var client = vis.getClient();
               //remove visists on client side
               client.getVisits().remove(vis);
               var patient = vis.getPatient();
               //remove visists on patient side
               patient.getVisits().remove(vis);
               //deleting visit from database
               visitRepository.deleteById(vis.getId_visit());
           });
        }


    }
}
