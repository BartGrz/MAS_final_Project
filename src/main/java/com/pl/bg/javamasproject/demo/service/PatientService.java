package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.ClientRepository;
import com.pl.bg.javamasproject.demo.adapter.MedicalCardRepository;
import com.pl.bg.javamasproject.demo.adapter.PatientRepository;
import com.pl.bg.javamasproject.demo.adapter.VaccinesRepository;
import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.model.MedicalCard;
import com.pl.bg.javamasproject.demo.model.Patient;

import com.pl.bg.javamasproject.demo.model.VaccinationReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    private PatientRepository patientRepository;
    private MedicalCardRepository medicalCardRepository;
    private ClientRepository clientRepository;

    public PatientService(PatientRepository patientRepository, MedicalCardRepository medicalCardRepository, ClientRepository clientRepository ) {
        this.patientRepository = patientRepository;
        this.medicalCardRepository = medicalCardRepository;
        this.clientRepository = clientRepository;
    }

    public void createPatient(Patient patient, Client client, Enum<MedicalCard.SpeciesTypes> species, int age) {

        if (clientRepository.existsById(client.getId_client())) {
            if (!patientRepository.existsById(patient.getId_patient())) {

                //creating new object
                var medicalCard = new MedicalCard(species, age);
                //adding object to reference on patient side
                patient.setMedicalCard(medicalCard);
                //adding object to reference on medicalCard side
                medicalCard.setPatient(patient);
                //adding object to reference on patient side
                patient.setClient(client);
                //adding object to reference on client side
                client.getPatients().add(patient);
                medicalCardRepository.save(medicalCard);
                patientRepository.save(patient);
                logger.info(" Patient added");

            } else {
                logger.warn(" Patient already exist");
            }
        } else {
            logger.warn("there is no client with given id");
        }
    }


}
