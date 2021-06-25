package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.MedicalCardRepository;
import com.pl.bg.javamasproject.demo.adapter.PatientRepository;
import com.pl.bg.javamasproject.demo.adapter.VaccinationReceivedRepository;
import com.pl.bg.javamasproject.demo.adapter.VaccinesRepository;
import com.pl.bg.javamasproject.demo.model.MedicalCard;
import com.pl.bg.javamasproject.demo.model.Vaccines;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MedicalCardServiceTest {


    @Test
    public void checkIfMedicalServiceMethod_addingVaccine_working() {

        //given
        var medicalCardRepo = mock(MedicalCardRepository.class);
        var vaccineRepo = mock(VaccinesRepository.class);
        var vaccinesReceivedRepo = mock(VaccinationReceivedRepository.class);
        var patientRepository = mock(PatientRepository.class);
        var medicalCard = new MedicalCard(MedicalCard.SpeciesTypes.BIRD,20);
        var vaccine = mock(Vaccines.class) ;
        var vaccine2 =new Vaccines(Vaccines.VaccineType.CANINE_LEPTOSPIRA) ;

        Set<String> test = new HashSet<>();
        test.add(Vaccines.VaccineType.CANINE_RABIES.toString());
        test.add(Vaccines.VaccineType.BORTADELLA_BRONCHHISEPTICA.toString());

        //when
        when(vaccineRepo.findIdByName(vaccine.getName())).thenReturn(1);
        when(vaccineRepo.findByName("CANINE_RABIES")).thenReturn(Optional.of(vaccine));
        when(vaccineRepo.findByName("CANINE_LEPTOSPIRA")).thenReturn(Optional.of(vaccine2));
        when(medicalCardRepo.findById(anyInt())).thenReturn(Optional.of(medicalCard));
        when(medicalCardRepo.findAllVaccinesReceivedById(anyInt())).thenReturn(test);
        vaccine2.setDate_of_vaccination(LocalDate.of(2021,9,18));

        assertEquals(vaccine2.getDate_of_vaccination(),LocalDate.of(2021,9,18));
        //system under test
        MedicalCardService medicalCardService = new MedicalCardService(vaccineRepo,medicalCardRepo,vaccinesReceivedRepo, patientRepository);
        medicalCardService.addVaccineToMedicalCard(1, Vaccines.VaccineType.CANINE_RABIES, LocalDate.of(2021,12,15));
        test.add(Vaccines.VaccineType.CANINE_RABIES.toString());
        medicalCardService.addVaccineToMedicalCard(1, Vaccines.VaccineType.CANINE_LEPTOSPIRA, LocalDate.of(2021,12,15));








    }

}