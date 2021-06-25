package com.pl.bg.javamasproject.demo.holder;

import com.pl.bg.javamasproject.demo.model.*;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class VisitInformation {

    @Getter
    private Visit visit;
    private Doctor doctor;
    private Patient patient;
    private Client client;
    private Map<String,Double> costMap = new HashMap<>();
    private Office office;

    @Getter
    @Setter
    private String client_name ;
    @Getter
    @Setter
    private String patient_name;
    @Getter
    @Setter
    private int office_number;
    @Getter
    @Setter
    private LocalTime beginingOfVisit;
    @Getter
    @Setter
    private LocalTime endOfVisit;
    @Getter
    @Setter
    private LocalDate dateOfVisit;
    @Getter
    @Setter
    private double overall_cost;

    public VisitInformation(Visit visit) {

        this.visit = visit;
        this.doctor = visit.getDoctor();
        this.client =visit.getClient();
        this.client_name = client.getName();
        this.patient=visit.getPatient();
        this.patient_name=patient.getName();
        this.beginingOfVisit=visit.getBeginning_time();
        this.endOfVisit=visit.getEnding_time();
        this.dateOfVisit=visit.getDate_of_visit();
        this.office = doctor.getOffice();
        this.office_number = office.getNumber();

        costMap.put(Visit.VisitType.CITO.toString(),300.0);
        costMap.put(Visit.VisitType.CONTROL.toString(),100.0);
        costMap.put(Visit.VisitType.OPERATION.toString(),350.0);
        costMap.put(Visit.VisitType.STOMATOLOGICAL_VISIT.toString(),180.0);
        costMap.put(Visit.VisitType.POSTOPERATION.toString(),50.0);
        costMap.put(Visit.VisitType.CARDIOLOGY.toString(),200.0);

        this.overall_cost = getVisitOverallCost();

    }

    private double getVisitOverallCost() {

        return costMap.get(visit.getVisit_type().toUpperCase())+(costMap.get(visit.getVisit_type().toUpperCase())*doctor.getBonus()/100);


    }

}
