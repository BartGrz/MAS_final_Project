package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Reception")
public class Reception extends Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_reception;

    public Reception(String name, String last_name, String phone_number, String email_adress, String PESEL, Enum<ContractType> contract_type, LocalDate date_of_employment, LocalTime begin_hour, LocalTime end_hour) {
        super(name, last_name, phone_number, email_adress, PESEL, contract_type, date_of_employment);
    }

    public Reception() {
    }

    @Override
    double getIncome() {
        return 0;
    }
}
