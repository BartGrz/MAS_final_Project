package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.DoctorRepository;
import com.pl.bg.javamasproject.demo.adapter.MedicalEquipmentRepository;
import com.pl.bg.javamasproject.demo.adapter.OfficeRepository;
import com.pl.bg.javamasproject.demo.model.Doctor;
import com.pl.bg.javamasproject.demo.model.Office;
import com.pl.bg.javamasproject.demo.model.VetEquipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    private static final Logger logger = LoggerFactory.getLogger(OfficeService.class);

    private OfficeRepository officeRepository;
    private DoctorRepository doctorRepository;
    private MedicalEquipmentRepository medicalEquipmentRepository;

    public OfficeService(OfficeRepository officeRepository, DoctorRepository doctorRepository, MedicalEquipmentRepository medicalEquipmentRepository) {
        this.officeRepository = officeRepository;
        this.doctorRepository = doctorRepository;
        this.medicalEquipmentRepository = medicalEquipmentRepository;
    }

    public Office createOffice(int number, Doctor doctor, int... floor) {

        if (!officeRepository.existbyNumber(number)) {
            if (doctorRepository.existsById(doctor.getId_doctor())) {
                Office createNew = null;
                if (floor.length > 0) {
                    createNew = new Office(number, doctor, floor[0]);
                } else {
                    createNew = new Office(number, doctor);
                }
                officeRepository.save(createNew);
                logger.info("Office created ");
                return createNew;
            }

        }else {
            logger.warn("Office with given number already exist ");
        }
        return null;

    }
   // public VetEquipment
}
