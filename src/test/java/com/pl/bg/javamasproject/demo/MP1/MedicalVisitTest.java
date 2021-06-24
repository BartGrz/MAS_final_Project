package com.pl.bg.javamasproject.demo.MP1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;


class MedicalVisitTest {



    @Test
    @DisplayName("check if map is filled by constructor and doctor object could be found")
    void findIfFindingDoctorAssosicated_ok() {
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);
        Pet pet = new Pet("foo", Pet.Species.MAMMAL.toString(), 2009);
        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.CONTROL,pet , doctor);

        //System.out.println(medicalVisit.findDoctorBy(1));
    }
}