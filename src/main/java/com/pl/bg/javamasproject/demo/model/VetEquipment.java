package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class VetEquipment {


    @Getter
    @Setter
    private String serial_number;
    @Getter
    @Setter
    private LocalDate date_of_purchase;
    @Getter
    @Setter
    private LocalDate last_service_date;
    @Getter
    @Setter
    private boolean reservation;
    @OneToOne
    @Getter
    @Setter
    private Office office;

    public VetEquipment(String serial_number, LocalDate date_of_purchase, LocalDate last_service_date, boolean reservation) {
        this.serial_number = serial_number;
        this.date_of_purchase = date_of_purchase;
        this.last_service_date = last_service_date;
        this.reservation = reservation;
    }

    public VetEquipment() {
    }
}
