package com.pl.bg.javamasproject.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Table without pk is mapped via @Embeddable annotation on class representing its fields
 * Table with @Entity annotation is linked with classes and has fields representing that connection
 * @field technician is represented by id_technician in @Embeddable class
 * @field visit is represented by id_visit in @Embeddable class
 * As hibernate need to have ID of entity, it is delivered by @EmbeddedId
 */
@Entity
@Table(name = "Tech_Visit")
public class TechVisit {

    @EmbeddedId
    @Getter
    private TechVisitPK id;
    @ManyToOne
    @JoinColumn(name = "id_technician", insertable = false,updatable = false)
    @Getter
    @Setter
    private Technician technician;
    @ManyToOne
    @JoinColumn(name = "id_visit",insertable = false,updatable = false)
    @Getter
    @Setter
    private Visit visit;

    public TechVisit() {
    }
}

@Embeddable
class TechVisitPK implements Serializable {

    private int id_technician;
    private int id_visit;

}
