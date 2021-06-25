package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Vaccines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaccinesRepository extends JpaRepository<Vaccines, Integer> {

    @Override
    List<Vaccines> findAll();

    @Override
    Vaccines save(Vaccines vaccines);

    @Override
    Optional<Vaccines> findById(Integer integer);

    @Query(value = "select v from Vaccines v where v.name like :name")
    Optional<Vaccines> findByName(@Param("name") String name);

    @Query(value = "select v.id_vaccine from Vaccines v where v.name like :name")
    Integer findIdByName(@Param("name") String vaccineName);

    @Override
    void deleteById(Integer integer);
}
