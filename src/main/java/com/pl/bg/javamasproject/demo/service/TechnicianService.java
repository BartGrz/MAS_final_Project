package com.pl.bg.javamasproject.demo.service;

import com.pl.bg.javamasproject.demo.adapter.*;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {
    private final static Logger logger = LoggerFactory.getLogger(TechnicianService.class);

    private TechnicianRepository technicianRepository;
    private TechVisitRepository techVisitRepository;
    private VisitRepository visitRepository;


    public TechnicianService(TechnicianRepository technicianRepository, TechVisitRepository techVisitRepository,
                        VisitRepository visitRepository
    ) {
        this.technicianRepository = technicianRepository;
        this.techVisitRepository = techVisitRepository;
        this.visitRepository = visitRepository;
    }
    /**
     * adding technician to visit -> visit object will be added to container in Technician class and Object technician will be assaigjned to variable in Visit class
     * @param id_technician -> if operation will completed it will be added to Tech_Visit database
     * @param id_visit -> if operation will completed it will be added to Tech_Visit database
     * @result two keys will be added to database which is representing the Optional connection between entities
     */
    public void assaignTechnicianToVisit(int id_technician, int id_visit) {

        var technician = technicianRepository.findById(id_technician).get();
        var visit = visitRepository.findById(id_visit).get();

        if (visitRepository.existsById(visit.getId_visit())) {
            if (visit.getTechnician() == null) {
                visit.setTechnician(technician);
                technician.getVisits().add(visit);
                techVisitRepository.saveTechnicianAndVisit(technician.getId_technician(), visit.getId_visit());
                logger.info("Technician assaigned to visit");
            } else {
                logger.warn("Technician with id" + techVisitRepository.findByIdAndVisit(visit.getId_visit()) + " " + " has been assaigned to visit already");
            }
        } else {
            logger.error("VISIT NOT FOUND ");
        }
    }
}
