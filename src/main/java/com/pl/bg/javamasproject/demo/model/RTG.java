package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RTG")
public class RTG extends VetEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_RTG;
    @Getter
    @Setter
    private int available_plates;
    @Getter
    @Setter
    private int protective_aprons;

    public RTG(String serial_number, LocalDate date_of_purchase, LocalDate last_service_date, boolean reservation, int available_plates, int protective_aprons) {
        super(serial_number, date_of_purchase, last_service_date, reservation);
        this.available_plates = available_plates;
        this.protective_aprons = protective_aprons;
    }

    public RTG() {
    }
}
