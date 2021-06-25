package com.pl.bg.javamasproject.demo.adapter;

import com.pl.bg.javamasproject.demo.model.Technician;
import com.pl.bg.javamasproject.demo.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Integer> {

    @Override
    List<Visit> findAll();
    @Override
    Visit save(Visit visit);
    @Override
    Optional<Visit> findById(Integer integer);

    @Query(value = "select t.technician from TechVisit t  where t.visit.id_visit=:id ")
    Optional<Technician> findByIdFromEmbedded(@Param("id") int id_visit);

    @Transactional
    @Modifying
    @Query(value = "delete from Visit where id_visit = :id")
    void removeById(@Param("id") int id_visit);

    @Override
    void delete(Visit visit);
}
