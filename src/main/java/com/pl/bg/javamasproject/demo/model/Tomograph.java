package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tomograph")
public class Tomograph extends VetEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_tomograph;
    @Getter
    @Setter
    private double contrast;

    public Tomograph(String serial_number, LocalDate date_of_purchase, LocalDate last_service_date, boolean reservation, double contrast) {
        super(serial_number, date_of_purchase, last_service_date, reservation);
        this.contrast = contrast;
    }

    public Tomograph() {
    }
}
