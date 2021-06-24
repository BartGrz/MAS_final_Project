package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.VaccinationReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VaccinationReceivedRepository extends JpaRepository<VaccinationReceived,Integer> {



    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "INSERT INTO VACCINATION_RECEIVED (ID_MEDICAL_CARD, ID_VACCINE) values ( :id_medical_card,:id_vaccine )")
    void saveVaccineToMedicalCard(@Param("id_medical_card")int id_medical_card, @Param("id_vaccine") int id_vaccine);



}
