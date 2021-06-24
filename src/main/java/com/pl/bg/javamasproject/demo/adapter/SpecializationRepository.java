package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    @Override
    List<Specialization> findAll();

    @Query(value = "select s.id_specialization from Specialization s where s.name like :name")
    int findByName(@Param("name") String name);

    @Override
    Specialization save(Specialization specialization);
}
