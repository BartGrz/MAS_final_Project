package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Office_hours")
public class OfficeHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_office_hours;
    @Getter
    @Setter
    private String monday;
    @Getter
    @Setter
    private String tuesday;
    @Getter
    @Setter
    private String wednesday;
    @Getter
    @Setter
    private String thursday;
    @Getter
    @Setter
    private String friday;
    @Getter
    @Setter
    private String saturday;
    @Getter
    @Setter
    private String sunday;


    public enum Days {
        MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
    }
}


