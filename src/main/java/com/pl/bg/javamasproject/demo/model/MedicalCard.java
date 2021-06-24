package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medical_card")
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_medical_card;
    @Getter
    @Setter
    private String species;
    @Getter
    @Setter
    private int age;
    @OneToOne
    @JoinColumn(name = "id_patient")
    @Getter
    @Setter
    private Patient patient;
    @Transient
    @Getter
    @Setter
    private Set<String> vaccines;

    public MedicalCard(Enum<SpeciesTypes> species, int age) {
        this.species = species.toString();
        this.age = age;
    }

    public MedicalCard() {
    }
    public enum SpeciesTypes {REPTILE,MAMMAL,AMPHIBIAN,BIRD}
}

