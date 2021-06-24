package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_patient;
    @Getter
    @Setter
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_client")
    @Getter
    @Setter
    private Client client;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Set<Visit> visits;
    @OneToOne
    @JoinColumn(name = "id_medical_card")
    @Getter
    @Setter
    private MedicalCard medicalCard;

    public Patient(String name, Client client) {
        this.name = name;
        this.client = client;
    }

    public Patient() {
    }
}

