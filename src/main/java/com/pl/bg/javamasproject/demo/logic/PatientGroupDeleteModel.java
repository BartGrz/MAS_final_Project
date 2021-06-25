package com.pl.bg.javamasproject.demo.logic;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class PatientGroupDeleteModel {

    private static final Logger logger = LoggerFactory.getLogger(PatientGroupDeleteModel.class);

    private MedicalCardRepository medicalCardRepository;
    private VaccinationReceivedRepository vaccinationReceivedRepository;
    private PatientRepository patientRepository;
    private VisitRepository visitRepository;


    public PatientGroupDeleteModel(MedicalCardRepository medicalCardRepository,
                                   VaccinationReceivedRepository vaccinationReceivedRepository, PatientRepository patientRepository,
                                   VisitRepository visitRepository) {

        this.medicalCardRepository = medicalCardRepository;
        this.vaccinationReceivedRepository = vaccinationReceivedRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
    }
    /**
     * Removing patient with all its references
     * @param patient
     */
    public void deleteWithReferences(Patient patient) {

        var medicalCard = patient.getMedicalCard();

        if (patientRepository.existsById(patient.getId_patient())) {
            //finding client linked wth patient
            var client = patient.getClient();
            //removing reference on client side
            client.getPatients().remove(patient);
            /*
            removing all visits in whic patient was involved
             */
            var visits = patient.getVisits();
            visits.forEach(visit -> {
                client.getVisits().remove(visit);
                var doctor = visit.getDoctor();
                doctor.getVisits().remove(visit);
                visitRepository.removeById(visit.getId_visit());
            });

            var vaccinesReceived = medicalCard.getVaccinationReceived().stream().collect(Collectors.toSet());
            if (!vaccinesReceived.isEmpty()) {
                //removing references with VaccinationReveived
                vaccinationReceivedRepository.deleteById(medicalCard.getId_medical_card());
            }

            //medicalCard is deleted
            medicalCardRepository.deleteById(medicalCard.getId_medical_card());
            //patient is deleted
            patientRepository.deleteById(patient.getId_patient());
            logger.info("Patient deleted with references");
        } else {
            logger.warn("Patient does not exist");
        }
    }
}
