package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.MedicalEquipmentRepository;
import com.pl.bg.javamasproject.demo.adapter.OfficeRepository;
import com.pl.bg.javamasproject.demo.model.Office;
import com.pl.bg.javamasproject.demo.model.RTG;
import com.pl.bg.javamasproject.demo.model.Tomograph;
import com.pl.bg.javamasproject.demo.model.VetEquipment;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MedicalEquipmentService<T> {

    private static final Logger logger = LoggerFactory.getLogger(MedicalEquipmentService.class);

    private OfficeRepository officeRepository;
    private MedicalEquipmentRepository medicalEquipmentRepository;

    public MedicalEquipmentService(OfficeRepository officeRepository, MedicalEquipmentRepository medicalEquipmentRepository) {
        this.officeRepository = officeRepository;
        this.medicalEquipmentRepository = medicalEquipmentRepository;
    }

    /**
     * creating new object of MedicalEquipment (in system are only ones that cannot be moved between offices) if it is instacnce of VetEquipment indicates putting it in office
     *
     * @param t
     * @param office
     */
    public void createNewEquipment(T t, Office office) {

        VetEquipment vetEquipment = (VetEquipment) t;
        if (t instanceof Tomograph) {
            if (!medicalEquipmentRepository.findBySerialNumberTomo(((Tomograph) t).getSerial_number())) {
                vetEquipment = (Tomograph) t;
            }
        } else if (t instanceof RTG) {
            if (!medicalEquipmentRepository.findBySerialNumberRTG(((RTG) t).getSerial_number())) {
                vetEquipment = (RTG) t;
            }
        }
        if (officeRepository.existsById(office.getId_office())) {
            if (office.getVetEquipment() == null) {

                var officeExist = officeRepository.findById(office.getId_office()).get();
                officeExist.setVetEquipment(vetEquipment);
                ((VetEquipment) t).setOffice(office);
                medicalEquipmentRepository.save((VetEquipment) t);

            } else {
                logger.info("some Equipment is already in office");
            }
            logger.info("Equipment added");
        }
    }



    /**
     * deleting object of MedicalEquipment (in system are only ones that cannot be moved between offices) indicates removing it from the office
     *
     * @param t (instance of VetEquipment)
     */
    public void removeEquipment(T t) {

        if (t instanceof VetEquipment) {
            var office = ((VetEquipment) t).getOffice();
            office.setVetEquipment(null);
            medicalEquipmentRepository.delete((VetEquipment) t);
            logger.warn("equipment removed");
        }
    }

}
