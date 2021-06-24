package com.pl.bg.javamasproject.demo.model;

import com.pl.bg.javamasproject.demo.adapter.DoctorRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Doctor")
@Component
public class Doctor extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id_doctor;
    @Getter
    @Setter
    private double bonus;
    @Getter
    @Setter
    private String medical_license_no;
    @Getter
    @Setter
    private String contract_type;
    @OneToMany(mappedBy = "doctor", targetEntity = DoctorSpec.class,fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<DoctorSpec> doctorSpecs;
    @Transient
    @Getter
    @Setter
    private Set<String> doctorSpecializations = new HashSet<>();
    @Getter
    @Setter
    @OneToOne(mappedBy = "doctor")
    private Office office;
    @OneToOne(mappedBy = "doctor",fetch = FetchType.EAGER)
    @Getter
    @Setter
    private DoctorOfficeHours doctorOfficeHours;
    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Visit> visits;
    public Doctor(String name, String last_name, String phone_number, String email_adress, String PESEL,
                  Enum<ContractType> contract_type, LocalDate date_of_employment,
                  double bonus, String medical_license_no
    ) {
        super(name, last_name, phone_number, email_adress, PESEL, contract_type, date_of_employment);
        this.bonus = bonus;
        this.medical_license_no = medical_license_no;
        this.contract_type = contract_type.toString();
    }

    public void addSpecialization(Enum<Specialization.SpecializationTypes> specialization) {
        doctorSpecializations.add(specialization.toString());
    }

    public Doctor() {
    }

    @Override
    double getIncome() {
        return 0;
    }

    private void fillHoursSet() {

    }

    public void fillSpecializationSet(int id_doctor, DoctorRepository doctorRepository) {
        doctorSpecializations = doctorRepository.getAllSpecializations(id_doctor);
        System.out.println(doctorSpecializations);


    }
}



