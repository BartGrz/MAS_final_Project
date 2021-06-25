package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MedicalCardRepository extends JpaRepository<MedicalCard, Integer> {

    @Override
    List<MedicalCard> findAll();

    @Override
    <S extends MedicalCard> S save(S s);

    @Override
    Optional<MedicalCard> findById(Integer integer);

    @Query(nativeQuery = true, value = "SELECT name from VACCINES join VACCINATION_RECEIVED VR on VACCINES.ID_VACCINE = VR.ID_VACCINE where VR.ID_MEDICAL_CARD=:id")
    Set<String> findAllVaccinesReceivedById(@Param("id") int id_medical_card);

    @Override
    void deleteById(Integer integer);
}
