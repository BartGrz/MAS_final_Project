package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.TechVisit;
import com.pl.bg.javamasproject.demo.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TechVisitRepository extends JpaRepository<TechVisit,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO TECH_VISIT (ID_TECHNICIAN, ID_VISIT) values ( :id_technician,:id_visit )")
    void saveTechnicianAndVisit(@Param("id_technician") int id_technician, @Param("id_visit")int id_visit);

    @Query(value = "select t.technician from TechVisit t join Visit v on t.visit.id_visit =v.id_visit where v.id_visit=:id")
    Optional<Technician> findByIdAndVisit(@Param("id") int id_visit);

    @Query(nativeQuery = true, value = "select count(*) >0  from TECH_VISIT where ID_VISIT =:id")
    boolean existsByIdVisit(@Param("id") Integer id_visit);
    @Query(nativeQuery = true, value = "select count(*) >0  from TECH_VISIT where ID_TECHNICIAN =:id")
    boolean existsByIdTechnician(@Param("id") Integer id_technician);


}
