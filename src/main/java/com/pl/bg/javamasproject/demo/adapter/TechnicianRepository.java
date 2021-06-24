package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

    @Override
    List<Technician> findAll();
    @Override
    Technician save (Technician technician);
    @Override
    Optional<Technician> findById(Integer integer);

}
