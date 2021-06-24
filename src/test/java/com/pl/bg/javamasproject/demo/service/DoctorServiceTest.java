package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DoctorServiceTest {

    @Test
    void checkIfService_works() throws NoSuchFieldException, IllegalAccessException {
        //Repo mocking
        var docRepo = mock(DoctorRepository.class);
        var specRepo = mock(SpecializationRepository.class);
        var docSpecRepo = mock(DoctorSpecRepository.class);
        var doctorService = new DoctorService(docRepo, specRepo, docSpecRepo);
        //given
        var doctor = new Doctor("a", "b", "c", "d", "a", Employee.ContractType.B2B, LocalDate.of(2020,10,10), 10.5, "122564");
        doctor.setId_doctor(1);
        doctor.setDoctorSpecializations(new HashSet<>());
        doctor.getDoctorSpecializations().add("ONCOLOGIST");
        var special = new Specialization(Specialization.SpecializationTypes.STOMATOLOGIST);

        //when
        when(docRepo.findById(1)).thenReturn(Optional.of(doctor));
        when(specRepo.findById(anyInt())).thenReturn(Optional.of(special));
        when(specRepo.findByName(special.getName())).thenReturn(3);

        //then

        doctorService.addSpecialization(doctor.getId_doctor(), Specialization.SpecializationTypes.ONCOLOGIST);
    }

    @Test
    void checkIfGetDoctorsHoursWorks_ifDoctorAlreadyHasAnyVisitsAssaigned() throws ParseException {

        var docRepo = mock(DoctorRepository.class);
        var specRepo = mock(SpecializationRepository.class);
        var docSpecRepo = mock(DoctorSpecRepository.class);
        var officeHours = mock(com.pl.bg.javamasproject.demo.model.OfficeHours.class);

        var client = mock(Client.class);
        var patient = mock(Patient.class);
        var beginTime = LocalTime.of(10, 30);
        var endTime = LocalTime.of(11, 30);
        var date_of_visit = LocalDate.now();
        var doctor = new Doctor("a", "b", "c", "d", "a", Employee.ContractType.B2B, LocalDate.of(2020, 02, 10)
                , 10.5, "122564");

        var visit = new Visit("STOMATOLOGICAL_VISIT", client, doctor, patient, beginTime, endTime, date_of_visit);
        var doctorService = new DoctorService(docRepo, specRepo, docSpecRepo);

        List<OfficeHours> setOfHours = new ArrayList<>();
        setOfHours.add(officeHours);
        doctor.setId_doctor(1);
        doctor.setVisits(new HashSet<>());
        doctor.getVisits().add(visit);
        Set<Visit> set = new HashSet<>();
        set.add(visit);
        //then
        assertEquals(doctor.getVisits(),set);
       // assertEquals(officeHours.getThursday(),"09:30 - 18:30");
        //when
        when(docRepo.findDoctorsOfficeHours(anyInt())).thenReturn(setOfHours);
        when(docRepo.findByLicenseNo(any())).thenReturn(Optional.of(doctor));
        // officeHours.setThursday("09:30 - 18:30");
        when(officeHours.getThursday()).thenReturn("09:30 - 18:30");

       doctorService.getDoctorsHours(doctor,date_of_visit);

    }

}