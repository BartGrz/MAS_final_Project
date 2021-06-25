package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office,Integer> {

    @Override
    List<Office> findAll();

    @Override
    <S extends Office> S save(S s);

    @Override
    Optional<Office> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Query(value = "select o from Office o where o.number=:number")
    boolean existbyNumber(@Param("number")int id);
}
