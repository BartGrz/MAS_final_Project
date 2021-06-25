package com.pl.bg.javamasproject.demo.logic;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PatientGroupDeleteModelTest {

    @Test
    void checkIfPatientGroupModelRemovingWithReferences_works() {

        var patientRepository = mock(PatientRepository.class);
        var vaccinationReceivedRepo = mock(VaccinationReceivedRepository.class);
        var medicalCardRepository = mock(MedicalCardRepository.class);
        var visitRepository = mock(VisitRepository.class);
        var doctor = mock(Doctor.class);
        var visit = mock(Visit.class);
        Set<Visit> set = new HashSet<>();
        set.add(visit);

        var patientGroupModel =
                new PatientGroupDeleteModel(medicalCardRepository, vaccinationReceivedRepo, patientRepository, visitRepository);
        var patient = mock(Patient.class);
        var medicalCard = mock(MedicalCard.class);
        var client = mock(Client.class);

        when(patientRepository.existsById(anyInt())).thenReturn(true);
        when(patient.getVisits()).thenReturn(set);
        when(visit.getDoctor()).thenReturn(doctor);
        when(patient.getClient()).thenReturn(client);
        when(patient.getMedicalCard()).thenReturn(medicalCard);

        patientGroupModel.deleteWithReferences(patient);

    }

}