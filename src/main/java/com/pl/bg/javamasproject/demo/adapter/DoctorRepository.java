package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Doctor;
import com.pl.bg.javamasproject.demo.model.OfficeHours;
import com.pl.bg.javamasproject.demo.model.Visit;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(nativeQuery = true,value = "SELECT s.name from Specialization s Join DOCTOR_SPEC DS on s.ID_SPECIALIZATION = DS.ID_SPECIALIZATION join DOCTOR D on D.ID_DOCTOR = DS.ID_DOCTOR where D.ID_DOCTOR=1;")
    Set<String> getAllSpecializations(@Param("id")int id);

    @Override
    Optional<Doctor> findById(Integer integer);

    //will help to find which doctor where chosen to be assagned to the visit

    @Query(value = "select d from Doctor d join DoctorSpec ds on ds.doctor.id_doctor=d.id_doctor where ds.specialization.name like :specialization_name")
    Set<Doctor> findBySpecialization(@Param("specialization_name")String specialization_name);
    @Query(value = "select o from OfficeHours o JOIN DoctorOfficeHours docOf on o.id_office_hours=docOf.officeHours.id_office_hours where docOf.doctor.id_doctor=:id_doctor")
    List<OfficeHours> findDoctorsOfficeHours(@Param("id_doctor")int id_doctor);

    @Query(value = "select v from Visit v where v.doctor.id_doctor=:id_doctor")
    List<Visit> findVisitsById(@Param("id_doctor") int id_doctor);
    @Transactional
    @Modifying
    @Query(nativeQuery = true , value ="UPDATE OFFICE set ID_DOCTOR= :id_doctor where ID_OFFICE=:id_office" )
    void insertIntoOffice(@Param("id_doctor")int id_doctor, @Param("id_office")int id_office);


}
