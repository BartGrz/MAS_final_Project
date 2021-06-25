package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.ClientRepository;
import com.pl.bg.javamasproject.demo.adapter.MedicalCardRepository;
import com.pl.bg.javamasproject.demo.adapter.PatientRepository;
import com.pl.bg.javamasproject.demo.model.*;
import org.assertj.core.internal.bytebuddy.implementation.MethodCall;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PatientServiceTest {


    @Test
    void checkIfAddingPatientWorks() throws NoSuchFieldException, IllegalAccessException {
        //given
        PatientRepository patientRepository = mock(PatientRepository.class);
        MedicalCardRepository medicalCardRepository = mock(MedicalCardRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        var patient = mock(Patient.class);
        var client = mock(Client.class);
        assertThat(client.getId_client()==1);
        //when
        when(client.getId_client()).thenReturn(1);
        when(clientRepository.existsById(1)).thenReturn(true);
        when(patientRepository.existsById(anyInt())).thenReturn(false);


        PatientService patientService = new PatientService(patientRepository, medicalCardRepository, clientRepository);
        patientService.createPatient(patient, client, MedicalCard.SpeciesTypes.REPTILE, 10);


    }

}