package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalTime;


@MappedSuperclass
public abstract class Employee extends Person {

    @Getter
    @Setter
    private String contract_type;
    @Getter
    @Setter
    private LocalDate date_of_employment;


    public Employee(String name, String last_name, String phone_number, String email_adress, String PESEL,
                    Enum<ContractType> contract_type, LocalDate date_of_employment

    ) {
        super(name, last_name, phone_number, email_adress, PESEL);
        this.contract_type = contract_type.toString();
        this.date_of_employment = date_of_employment;

    }

    public Employee() {
    }

    abstract double getIncome();

    public enum ContractType {
        B2B, FULL_TIME, CONTRACT_OF_MANDATE
    }
}
