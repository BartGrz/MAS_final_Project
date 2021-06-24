package com.pl.bg.javamasproject.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Vaccination_Received")
public class VaccinationReceived {

    @EmbeddedId
    @Getter
    private VaccinationReceivedPK id;

    @ManyToOne
    @JoinColumn(name = "id_medical_card", insertable = false, updatable = false)
    @Getter
    @Setter
    private MedicalCard medicalCard;
    @ManyToOne
    @JoinColumn(name = "id_vaccine", insertable = false, updatable = false)
    @Getter
    @Setter
    private Vaccines vaccines;

}

@Embeddable
class VaccinationReceivedPK implements Serializable {

    private int id_vaccine;
    private int id_medical_card;

}