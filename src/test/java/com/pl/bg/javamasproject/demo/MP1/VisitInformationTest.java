package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class VisitInformationTest {

    @Test
    void testIfDoctorAndMedicalVisitClasses_getTheObjectOFVisitInformationClass(){
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);

        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.OPERATION,
                new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)),doctor);

        var visitInfo = new VisitInformation(medicalVisit,doctor);
        //System.out.println(doctor.getVisitInformationsSet() + " medical visit set " + medicalVisit.getVisitInformationsSet());

    }

}