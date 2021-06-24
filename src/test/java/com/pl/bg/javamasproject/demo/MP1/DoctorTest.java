package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;

public class DoctorTest {

    @Test
    void checkIfDoctorClassMethodsWork() {
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);

        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.OPERATION, new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)), doctor);

        doctor.addVisit(medicalVisit);
        // doctor.addVisit(medicalVisit);
       // System.out.println(medicalVisit.findDoctorBy(1));
    }

    @Test
    void findIfMethod_findVisit_returnsOptional() {
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);
        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.OPERATION, new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)), doctor);
        doctor.addPatientToDoctor(new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)));
       // doctor.addVisit(medicalVisit);

      //  System.out.println(doctor.findMedicalVisitBy(anyInt()));
    }

}
