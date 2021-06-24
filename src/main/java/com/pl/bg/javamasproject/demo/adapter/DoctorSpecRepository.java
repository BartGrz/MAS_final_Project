package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.DoctorSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DoctorSpecRepository extends JpaRepository<DoctorSpec,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO DOCTOR_SPEC (ID_DOCTOR, ID_SPECIALIZATION) values ( :id_doctor,:id_specialization )")
    void saveSpecialization(@Param("id_doctor") int id_doctor, @Param("id_specialization")int id_specialization);



}
