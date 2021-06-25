package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_office;
    @Getter
    @Setter
    private int number;
    @Getter
    @Setter
    private int floor;
    @OneToOne
    @JoinColumn(name = "id_doctor")
    @Getter
    @Setter
    private Doctor doctor;
    @Transient
    @Getter
    @Setter
    private VetEquipment vetEquipment;


    public Office(int number, Doctor doctor, int... floor) {
        this.number = number;
        if (floor.length > 0) {
            this.floor = floor[0];
        } else {
            this.floor = 0;
        }
        this.doctor = doctor;
    }

    public Office() {
    }
}
