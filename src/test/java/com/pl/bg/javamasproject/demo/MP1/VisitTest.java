package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VisitTest {

    @Test
    void checkIfInnerClassHasObject_ofOuterClass() {

        var visit = new Visit(LocalDateTime.now());
        visit.addInvolved(Visit.StaffType.PATIENT);
       // System.out.println(visit.getVisit().get(0).getInvolved());

    }

    @Test
    void checkIfOuterClassHasObject_ofInnerClass() {

        var visit = new Visit(LocalDateTime.now());
        visit.addInvolved(Visit.StaffType.PATIENT);
       // System.out.println(visit.getVisit().get(0).getVisits().get(0));
    }
}