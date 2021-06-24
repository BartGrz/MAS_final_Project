package com.pl.bg.javamasproject.demo.service;


import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.Doctor;
import com.pl.bg.javamasproject.demo.model.OfficeHours;
import com.pl.bg.javamasproject.demo.model.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    //TODO : 2021-06-23 add the most important method for use case scenario
        public void createVisit(int id_client, int id_patient, String visitType, int id_doctor, LocalTime beginTime, LocalTime endTime, LocalDate date_of_visit) {

            var doctor = doctorRepository.findById(id_doctor).get();
            var client = clientRepository.findById(id_client).get();
            var patient = clientRepository.findByPatientId(id_patient).get();

            logger.warn(beginTime + " " + endTime);
            var visit = new Visit(visitType,client,doctor,patient,beginTime,endTime,date_of_visit);
            visitRepository.save(visit);
            logger.info("Visit added");
        }


    }
