package com.pl.bg.javamasproject.demo.model;


import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.service.TechnicianService;
import com.pl.bg.javamasproject.demo.service.VisitService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisitTest {

    @Test
    public void checkIfAddingTechnicianToVisit_willWork() {
        //given
        var technicianRepo = mock(TechnicianRepository.class);
        var visitRepo = mock(VisitRepository.class);
        var techVisitRepo = mock(TechVisitRepository.class);
        var doctorRepo = mock(DoctorRepository.class);
        var clientRepository = mock(ClientRepository.class);

        var technician = new Technician("a", "b", "c", "d", "e", Employee.ContractType.CONTRACT_OF_MANDATE, LocalDate.of(2020, 10, 05));
        var doctor = new Doctor("a", "b", "c", "d", "a", Employee.ContractType.B2B, LocalDate.of(2020, 02, 10),10.5,"122564");
        var client = new Client("a", "b", "c", "d", "e", null);
        var patient = new Patient("foo", client);
        var visit = new Visit("CITO", client, doctor, patient, LocalTime.of(17,20),LocalTime.of(17,50),LocalDate.of(2020,2,10));


        techVisitRepo.saveTechnicianAndVisit(doctor.getId_doctor(), visit.getId_visit());

        technician.setVisits(new HashSet<>());
        technician.getVisits().add(visit);
        //when
        when(visitRepo.existsById(anyInt())).thenReturn(true);
        when(technicianRepo.existsById(anyInt())).thenReturn(false);
        when(visitRepo.findById(anyInt())).thenReturn(Optional.of(visit));
        when(technicianRepo.findById(anyInt())).thenReturn(Optional.of(technician));

        //then
        visit.setTechnician(null);
        assertEquals(visit.getTechnician(), null);
        //test
        var technicianService = new TechnicianService(technicianRepo,techVisitRepo,visitRepo);
        technicianService.assaignTechnicianToVisit(doctor.getId_doctor(), visit.getId_visit());


    }

    @Test
    void testMe() {
        System.out.println(LocalTime.of(10,30).compareTo(LocalTime.of(11,30)));
    }
}