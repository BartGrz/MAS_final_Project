package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Specialization")
public class Specialization {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_specialization;
    @Getter
    @Setter
    private String name;


    public Specialization(Enum<SpecializationTypes> spec) {
        this.name=spec.toString();
    }

    public Specialization() {
    }

    public enum SpecializationTypes {
        ONCOLOGIST, STOMATOLOGIST, INTERNIST, SURGEON
    }
}
