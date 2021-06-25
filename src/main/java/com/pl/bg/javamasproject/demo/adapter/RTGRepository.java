package com.pl.bg.javamasproject.demo.adapter;

import com.pl.bg.javamasproject.demo.model.RTG;
import com.pl.bg.javamasproject.demo.model.VetEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RTGRepository extends JpaRepository<RTG,Integer> {

    @Override
    List<RTG> findAll();

    @Override
    <S extends RTG> S save(S s);

    @Override
    Optional<RTG> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void delete(RTG vetEquipment);

    @Query(value = "select r from RTG r where r.serial_number = :serial_number")
    boolean findBySerialNumberRTG(@Param("serial_number")String serial_number);
}
