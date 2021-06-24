package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Vaccines")
public class Vaccines {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private int id_vaccine;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private LocalDate date_of_vaccination;
    @Getter
    @Setter
    private LocalDate date_of_second_dose;

    public Vaccines(Enum<VaccineType> name) {
        this.name = name.toString();

    }
    public Vaccines() {
    }
   public enum VaccineType {
        BORTADELLA_BRONCHHISEPTICA,CANINE_RABIES,CANINE_LEPTOSPIRA
    }
}
