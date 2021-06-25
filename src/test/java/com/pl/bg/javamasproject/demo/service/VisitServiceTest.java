package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VisitServiceTest {


    @Test
   public void checkIfAddingVisitWorks() {
            //given
        var docRepo = mock(DoctorRepository.class);
        var clientRepository = mock(ClientRepository.class);
        var visitRepo = mock(VisitRepository.class);
        var officeHours = mock(com.pl.bg.javamasproject.demo.model.OfficeHours.class);
        var client = mock(Client.class);
        var patient = mock(Patient.class);
        var beginTime = LocalTime.of(10, 30);
        var endTime = LocalTime.of(11, 30);
        var date_of_visit = LocalDate.now();
        var visitService = new VisitService(visitRepo,docRepo,clientRepository);
        var visit = mock(Visit.class);
        var doctor = new Doctor("a", "b", "c", "d", "a", Employee.ContractType.B2B, LocalDate.of(2020, 02, 10)
                , 10.5, "122564");
        doctor.setId_doctor(1);
        client.setClient_number("12544");
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setClient(client);
        visit.setBeginning_time(beginTime);
        visit.setEnding_time(endTime);
        visit.setDate_of_visit(date_of_visit);
        visit.setVisit_type("CONTROL");

        //when
        when(docRepo.findById(anyInt())).thenReturn(Optional.of(doctor));
        when(clientRepository.findByClientNumber(any())).thenReturn(Optional.of(client));
        when(clientRepository.findByPatientId(anyInt())).thenReturn(Optional.of(patient));
        //under test
        visitService.createVisit(visit);
    }

}