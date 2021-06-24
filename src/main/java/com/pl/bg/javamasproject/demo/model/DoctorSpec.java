package com.pl.bg.javamasproject.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Table without pk is mapped via @Embeddable annotation on class representing its fields
 * Table with @Entity annotation is linked with classes and has fields representing that connection
 * @field doctor is represented by id_doctor and @field specialization is represented by id_specialization in @Embeddable class
 * As hibernate need to have ID of entity, it is delivered by @EmbeddedId
 */
@Entity
@Table(name = "Doctor_Spec")
@Component
public class DoctorSpec implements Serializable{

    @EmbeddedId
    @Getter
    private DoctorSpecPK id;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_doctor",insertable = false,updatable = false)
   @Getter
   @Setter
   private Doctor doctor;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_specialization",insertable = false,updatable = false)
   @Getter
   @Setter
   private Specialization specialization;


}

@Embeddable
 class DoctorSpecPK implements Serializable {

    @Getter
    private int id_doctor;
    @Getter
    private int id_specialization;


}
