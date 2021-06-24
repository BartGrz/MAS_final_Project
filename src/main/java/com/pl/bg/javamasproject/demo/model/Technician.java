package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "Technician")
public class Technician extends Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_technician;
    @Transient
    @Getter
    @Setter
    private Set<Visit> visits;

    public Technician(String name, String last_name, String phone_number, String email_adress, String PESEL, Enum<ContractType> contract_type,
                      LocalDate date_of_employment
    ) {
        super(name, last_name, phone_number, email_adress, PESEL, contract_type, date_of_employment);
    }

    public Technician() {
    }

    @Override
    double getIncome() {
        return 0;
    }
   public enum Responsibilites{

   }
}
