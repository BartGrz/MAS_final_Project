package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.OfficeRepository;
import com.pl.bg.javamasproject.demo.adapter.RTGRepository;
import com.pl.bg.javamasproject.demo.adapter.TomographRepository;
import com.pl.bg.javamasproject.demo.model.Office;
import com.pl.bg.javamasproject.demo.model.RTG;
import com.pl.bg.javamasproject.demo.model.Tomograph;
import com.pl.bg.javamasproject.demo.model.VetEquipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MedicalEquipmentService<T> {


    private static final Logger logger = LoggerFactory.getLogger(MedicalEquipmentService.class);

    private OfficeRepository officeRepository;
    private RTGRepository rtgRepository;
    private TomographRepository tomographRepository;

    public MedicalEquipmentService(OfficeRepository officeRepository, RTGRepository rtgRepository, TomographRepository tomographRepository) {
        this.officeRepository = officeRepository;
        this.tomographRepository = tomographRepository;
        this.rtgRepository = rtgRepository;
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
            if (!tomographRepository.findBySerialNumberTomo(((Tomograph) t).getSerial_number())) {
                vetEquipment = (Tomograph) t;
                if (officeRepository.existsById(office.getId_office())) {
                    if (office.getVetEquipment() == null) {

                        var officeExist = officeRepository.findById(office.getId_office()).get();
                        officeExist.setVetEquipment(vetEquipment);
                        ((Tomograph) t).setOffice(office);
                        tomographRepository.save((Tomograph) t);
                        logger.info("Equipment added");
                    } else {
                        logger.info("some Equipment is already in office");
                    }
                }
            }
        } else if (t instanceof RTG) {
            if (!rtgRepository.findBySerialNumberRTG(((RTG) t).getSerial_number())) {
                vetEquipment = (RTG) t;
                if (officeRepository.existsById(office.getId_office())) {
                    if (office.getVetEquipment() == null) {

                        var officeExist = officeRepository.findById(office.getId_office()).get();
                        officeExist.setVetEquipment(vetEquipment);
                        ((RTG) t).setOffice((office));
                        rtgRepository.save((RTG) t);
                        logger.info("Equipment added");
                    } else {
                        logger.info("some Equipment is already in office");
                    }
                }

            }

        }
    }

    /**
     * deleting object of MedicalEquipment (in system are only ones that cannot be moved between offices) indicates removing it from the office
     *
     * @param t (instance of VetEquipment)
     */
    public void removeEquipment(T t) {

        Office office = null;
        if (t instanceof Tomograph) {
            if (tomographRepository.findBySerialNumberTomo(((Tomograph) t).getSerial_number())) {
                office.setVetEquipment(null);
                tomographRepository.delete((Tomograph) t);
            }

        } else if (t instanceof RTG) {
            if (rtgRepository.findBySerialNumberRTG(((RTG) t).getSerial_number())) {
                office.setVetEquipment(null);
                rtgRepository.delete((RTG) t);
            }
        }
        logger.warn("equipment removed");
    }


}