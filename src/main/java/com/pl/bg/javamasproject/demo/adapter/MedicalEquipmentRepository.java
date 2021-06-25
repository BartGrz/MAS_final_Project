package com.pl.bg.javamasproject.demo.adapter;

import com.pl.bg.javamasproject.demo.model.VetEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicalEquipmentRepository extends JpaRepository<VetEquipment,Integer> {

    @Override
    List<VetEquipment> findAll();

    @Override
    <S extends VetEquipment> S save(S s);

    @Override
    Optional<VetEquipment> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void delete(VetEquipment vetEquipment);

    @Query(value = "select t from Tomograph t where t.serial_number = :serial_number")
    boolean findBySerialNumberTomo(@Param("serial_number")String serial_number);
    @Query(value = "select r from RTG r where r.serial_number = :serial_number")
    boolean findBySerialNumberRTG(@Param("serial_number")String serial_number);
}
