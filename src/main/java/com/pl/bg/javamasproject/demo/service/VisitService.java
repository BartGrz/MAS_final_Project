package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VisitService {

    private final static Logger logger = LoggerFactory.getLogger(VisitService.class);

    private VisitRepository visitRepository;
    private DoctorRepository doctorRepository;
    private ClientRepository clientRepository;


    public VisitService(VisitRepository visitRepository, DoctorRepository doctorRepository, ClientRepository clientRepository) {

        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * main method which adding visit to database and returnin object of Visit type, logic in UI prevent from typing wrong data
     * @param visit -> after object of Visit is passed as parameter, method will added its fields to database via repository
     *              UI prevent user from typing wrong data, so there is no need to double check ex. if(doctor.exist) because system know it does
     * @return created visit, logic indicates that this object must be return
     */
        public void createVisit(Visit visit) {

            visitRepository.save(visit);
            logger.info("Visit added");

        }
        public void removeVisit(int id_visit) {

            if(visitRepository.findById(id_visit).get()!=null) {
                visitRepository.removeById(id_visit);
                logger.info("Visit with id ="+id_visit + " removed ");
            }

        }

    }
