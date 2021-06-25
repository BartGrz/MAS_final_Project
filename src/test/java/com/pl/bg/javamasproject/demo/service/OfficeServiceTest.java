package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.DoctorRepository;
import com.pl.bg.javamasproject.demo.adapter.RTGRepository;
import com.pl.bg.javamasproject.demo.adapter.OfficeRepository;
import com.pl.bg.javamasproject.demo.adapter.TomographRepository;
import com.pl.bg.javamasproject.demo.model.Doctor;
import com.pl.bg.javamasproject.demo.model.Office;
import org.junit.jupiter.api.Test;

import javax.security.auth.callback.TextOutputCallback;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OfficeServiceTest {

    @Test
    void createOffice() {
        //given
        var docRepo = mock(DoctorRepository.class);
        var officeRepo = mock(OfficeRepository.class);
        var rtgRepository= mock(RTGRepository.class);
        var tomographRepository = mock(TomographRepository.class);
        var doctor = mock(Doctor.class);
        var officeService = new OfficeService(officeRepo,docRepo, rtgRepository, tomographRepository);
        var office = mock(Office.class);
        office.setNumber(1);

        //when
        when(docRepo.existsById(anyInt())).thenReturn(true);
        //when office with given number exist
        when(officeRepo.existbyNumber(1)).thenReturn(true);
        //when office with given number !exist
        //when(officeRepo.existbyNumber(1)).thenReturn(false);
        //uder test
        officeService.createOffice(1,doctor);






    }
}