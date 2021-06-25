package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Override
    List<Patient> findAll();

    Patient save (Patient patient);

    @Override
    Optional<Patient> findById(Integer integer);
    @Override
    void deleteById(Integer integer);
}
