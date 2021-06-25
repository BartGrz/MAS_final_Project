package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Entity
@Table(name = "Visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_visit;
    @Getter
    @Setter
    private String visit_type;
    @ManyToOne
    @JoinColumn(name = "id_client")
    @Getter
    @Setter
    private Client client;
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    @Getter
    @Setter
    private Doctor doctor;
    @Getter
    @Setter
    @Transient
    private Technician technician;
    @Getter
    @Setter
    private LocalTime beginning_time;
    @Getter
    @Setter
    private LocalTime ending_time;
    @Getter
    @Setter
    private LocalDate date_of_visit;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    @Getter
    @Setter
    private Patient patient;

    //Techni
    public Visit(String visit_type, Client client, Doctor doctor, Patient patient,LocalTime beginning_time, LocalTime ending_time, LocalDate date_of_visit) {
        this.visit_type = visit_type;
        this.client = client;
        this.doctor = doctor;
        this.patient = patient;
        this.date_of_visit=date_of_visit;
        this.beginning_time=beginning_time;
        this.ending_time=ending_time;
    }
    public Visit() {
    }
    public enum VisitType {CITO, CONTROL,POSTOPERATION,OPERATION,STOMATOLOGICAL_VISIT,CARDIOLOGY
    }


}

