package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.adapter.RTGRepository;
import com.pl.bg.javamasproject.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    private static final Logger logger = LoggerFactory.getLogger(OfficeService.class);

    private OfficeRepository officeRepository;
    private DoctorRepository doctorRepository;
    private RTGRepository rtgRepository;
    private TomographRepository tomographRepository;
    MedicalEquipmentService medicalEqService = new MedicalEquipmentService<Tomograph>(officeRepository, rtgRepository, tomographRepository);

    public OfficeService(OfficeRepository officeRepository, DoctorRepository doctorRepository, RTGRepository rtgRepository, TomographRepository tomographRepository) {
        this.officeRepository = officeRepository;
        this.doctorRepository = doctorRepository;
        this.rtgRepository = rtgRepository;
        this.tomographRepository = tomographRepository;
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

        } else {
            logger.warn("Office with given number already exist ");
        }
        return null;

    }

    public Office getEquipment(VetEquipment vetEquipment) {

        if (vetEquipment instanceof Tomograph) {
            MedicalEquipmentService medicalEqService = new MedicalEquipmentService<Tomograph>(officeRepository, rtgRepository, tomographRepository);
            return medicalEqService.findOfficeWithEq(vetEquipment);
        }
        if (vetEquipment instanceof RTG) {
            MedicalEquipmentService medicalEqService = new MedicalEquipmentService<RTG>(officeRepository, rtgRepository, tomographRepository);
            return medicalEqService.findOfficeWithEq(vetEquipment);
        }

        return null;
    }
}
