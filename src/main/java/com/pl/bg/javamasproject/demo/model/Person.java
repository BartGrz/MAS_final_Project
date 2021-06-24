package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String last_name;
    @Getter
    @Setter
    private String phone_number;
    @Getter
    @Setter
    private String email_adress;
    @Getter
    @Setter
    private String PESEL;


    public Person(String name, String last_name, String phone_number, String email_adress, String PESEL) {
        this.name = name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email_adress = email_adress;
        this.PESEL = PESEL;
    }

    public Person() {

    }
}
