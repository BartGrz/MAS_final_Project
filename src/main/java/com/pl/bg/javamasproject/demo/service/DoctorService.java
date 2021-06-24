package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.DoctorRepository;
import com.pl.bg.javamasproject.demo.adapter.DoctorSpecRepository;
import com.pl.bg.javamasproject.demo.adapter.SpecializationRepository;
import com.pl.bg.javamasproject.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final static Logger logger = LoggerFactory.getLogger(DoctorService.class);

    private DoctorRepository doctorRepository;
    private SpecializationRepository specializationRepository;
    private DoctorSpecRepository doctorSpecRepository;

    public DoctorService(DoctorRepository doctorRepository, SpecializationRepository specializationRepository, DoctorSpecRepository doctorSpecRepository) {
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
        this.doctorSpecRepository = doctorSpecRepository;

    }

    /**
     * adding given specialization to doctor list of specializations - adding it to class list and to the DoctorSpec class saving it to database
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
     * By this method all possibles hours for visit are shown.
     *
     * @param doctor
     * @param date_of_visit
     * @return list of doctors office hour which are not assaignet to any visit
     * @throws ParseException
     */
    public List<LocalTime> getDoctorsHours(Doctor doctor, LocalDate date_of_visit) throws ParseException {

        var setOfDays = doctorRepository.findDoctorsOfficeHours(doctor.getId_doctor());


        //parsing LocalDate and finding which day of the week it will be
        /*
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = format.parse(date_of_visit.toString());
        DateFormat format2 = new SimpleDateFormat("EEEE", Locale.ENGLISH);

         */
        var ld = LocalDate.parse(date_of_visit.toString());
        int day = ld.getDayOfWeek().getValue();
        String beginning_hour;
        logger.info(" " + day);
        switch (day) { //Getting doctor's office hours from list 'setOfDays'
            case 1:
                beginning_hour = setOfDays.stream().map(OfficeHours::getMonday).collect(Collectors.joining());
                break;
            case 2:
                beginning_hour = setOfDays.stream().map(OfficeHours::getTuesday).collect(Collectors.joining());
                break;
            case 3:
                beginning_hour = setOfDays.stream().map(OfficeHours::getWednesday).collect(Collectors.joining());
                break;
            case 4:
                beginning_hour = setOfDays.stream().map(OfficeHours::getThursday).collect(Collectors.joining());
                logger.warn(" I AM HERE ");
                break;
            case 5:
                beginning_hour = setOfDays.stream().map(OfficeHours::getFriday).collect(Collectors.joining());
                break;
            case 6:
                beginning_hour = setOfDays.stream().map(OfficeHours::getSaturday).collect(Collectors.joining());
                break;
            case 7:
                beginning_hour = setOfDays.stream().map(OfficeHours::getSunday).collect(Collectors.joining());
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
        //else it will proceed to find beginning hour and ending hour from String type by split method,
        // then parsing first element of array as a beginning hour, second as ending and based on it i create loop
        // (how many hour is doctor woking this day : ex. 10:30 -18:30 = 8;
        String[] tab = beginning_hour.split(" - ");
        LocalTime time = LocalTime.parse(tab[0]);
        LocalTime endTime = LocalTime.parse(tab[1]);
        int length = endTime.getHour() - time.getHour();
        for (int i = 0; i < length; i++) {
            listOfHours.add(time);
            time = LocalTime.of(time.getHour() + 1, time.getMinute());
        }
        if(!doctor.getVisits().isEmpty()) {
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

            } else {

            }
        }
            logger.info("" + listOfHours);
            return listOfHours;
        }
    }
