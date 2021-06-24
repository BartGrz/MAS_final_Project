package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Doctor_Office_Hours")
public class DoctorOfficeHours {

    @EmbeddedId
    @Getter
    private DoctorOfficeHoursPK id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_office_hours",insertable = false,updatable = false)
    private OfficeHours officeHours;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_doctor",insertable = false,updatable = false)
    private Doctor doctor;

}

@Embeddable
class DoctorOfficeHoursPK implements Serializable {

    @Getter
    private int id_doctor;
    @Getter
    private int id_office_hours;

}
