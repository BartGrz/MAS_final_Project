package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.RTGRepository;
import com.pl.bg.javamasproject.demo.adapter.OfficeRepository;
import com.pl.bg.javamasproject.demo.adapter.TomographRepository;
import com.pl.bg.javamasproject.demo.model.Office;
import com.pl.bg.javamasproject.demo.model.RTG;
import com.pl.bg.javamasproject.demo.model.Tomograph;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class MedicalEquipmentServiceTest {


    @Test
    void addEquipment() {

        //given
        var rtgRepository = mock(RTGRepository.class);
        var officeRepo = mock(OfficeRepository.class);
        var tomo = mock(Tomograph.class);
        var tomographRepository = mock(TomographRepository.class);
        var office = mock(Office.class);

        tomo.setSerial_number("1111");

        //when
        when(officeRepo.existsById(anyInt())).thenReturn(true);
        when(officeRepo.findById(anyInt())).thenReturn(Optional.of(office));
        when(tomo.getSerial_number()).thenReturn("1111");
        when(rtgRepository.findBySerialNumberRTG(tomo.getSerial_number())).thenReturn(false);
        when(office.getVetEquipment()).thenReturn(null);

        //under test
        MedicalEquipmentService<Tomograph> medicalEquipmentService = new MedicalEquipmentService<>(officeRepo, rtgRepository, tomographRepository);
        medicalEquipmentService.createNewEquipment(tomo, office);


    }

    @Test
    void removeEquipment() {
        //given
        var medicalEqRepo = mock(RTGRepository.class);
        var officeRepo = mock(OfficeRepository.class);
        var tomographRepository = mock(TomographRepository.class);
        var rtg = mock(RTG.class);
        var office = mock(Office.class);

        //when
        when(officeRepo.existsById(anyInt())).thenReturn(false);
        when(officeRepo.findById(anyInt())).thenReturn(Optional.of(office));
        when(rtg.getOffice()).thenReturn(office);

        //under test
        MedicalEquipmentService<RTG> medicalEquipmentService = new MedicalEquipmentService<>(officeRepo, medicalEqRepo, tomographRepository);
        medicalEquipmentService.removeEquipment(rtg);
    }
}