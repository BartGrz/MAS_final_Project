package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.MedicalCardRepository;
import com.pl.bg.javamasproject.demo.adapter.VaccinationReceivedRepository;
import com.pl.bg.javamasproject.demo.adapter.VaccinesRepository;
import com.pl.bg.javamasproject.demo.model.Vaccines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MedicalCardService {
    public static final Logger logger = LoggerFactory.getLogger(MedicalCardService.class);

    private VaccinesRepository vaccinesRepository;
    private MedicalCardRepository medicalCardRepository;
    private VaccinationReceivedRepository vaccinationReceivedRepository;

    public MedicalCardService(VaccinesRepository vaccinesRepository, MedicalCardRepository medicalCardRepository, VaccinationReceivedRepository vaccinationReceivedRepository) {
        this.vaccinesRepository = vaccinesRepository;
        this.medicalCardRepository = medicalCardRepository;
        this.vaccinationReceivedRepository = vaccinationReceivedRepository;
    }

    /**
     * Method adding vaccine to the medical card of a patient by adding its name to container in MedicalCard and to database Vaccination_Received
     * @param id_medical_card -> key of medical_card entity
     * @param vaccineName     -> Enum of available vaccines
     * @param first_dose      -> date of vaccination will be added if field in class is null, if it will be after first dose it will be assaigned as @param second_dose
     *                        if it will be older then firstDose (assaigned before) nothing will change
     * @param second_dose     -> if is assaigned, and is after first dose else it will
     */
    public void addVaccineToMedicalCard(int id_medical_card, Enum<Vaccines.VaccineType> vaccineName, LocalDate first_dose, LocalDate... second_dose) {

        var medicalCard = medicalCardRepository.findById(id_medical_card).get();

        medicalCard.setVaccines(medicalCardRepository.findAllVaccinesReceivedById(id_medical_card));

        var vaccine = vaccinesRepository.findByName(vaccineName.toString()).get();
        var id_vaccine = vaccinesRepository.findIdByName(vaccineName.toString());

        if (!medicalCard.getVaccines().stream().anyMatch(s -> s.equals(vaccineName.toString()))) {
            logger.info("Vaccine " + vaccineName.toString() + " added to medical card with id " + id_medical_card);

            if (vaccine.getDate_of_vaccination() == null) {
                vaccine.setDate_of_vaccination(first_dose);
                vaccinesRepository.save(vaccine);
            } else if (first_dose.isBefore(vaccine.getDate_of_vaccination())&&second_dose.length == 0) {
                logger.warn("date of first vaccination given is diffrent then this in medical card, maybe so it is proccesed as second dose");
                vaccine.setDate_of_second_dose(first_dose);
                vaccinesRepository.save(vaccine);
            } else if (second_dose.length >0){
                if (second_dose[0].isAfter(first_dose) || second_dose[0].isAfter(vaccine.getDate_of_vaccination())) {
                    vaccine.setDate_of_second_dose(second_dose[0]);
                }
                vaccinationReceivedRepository.saveVaccineToMedicalCard(id_medical_card, id_vaccine);
                medicalCard.getVaccines().add(vaccineName.toString());
                logger.info("operation completed");
            }else {
                logger.error("given date is older than date of the first vaccine operation failed to success ");
            }
        } else {
            logger.error("vaccine " + vaccineName.toString() + "already added");
        }
    }
}
