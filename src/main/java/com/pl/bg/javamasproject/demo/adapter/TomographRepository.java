package com.pl.bg.javamasproject.demo.adapter;

import com.pl.bg.javamasproject.demo.model.Tomograph;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TomographRepository extends JpaRepository<Tomograph, Integer> {

    @Override
    List findAll();

    @Override
    Optional findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Tomograph> S save(S s);

    @Query(value = "select t from Tomograph t where t.serial_number = :serial_number")
    boolean findBySerialNumberTomo(@Param("serial_number")String serial_number);
}
