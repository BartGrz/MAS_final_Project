package com.pl.bg.javamasproject.demo.adapter;


import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Override
    List<Client> findAll();

    @Override
    Client  save(Client client);

    @Override
    Optional<Client> findById(Integer integer);
    @Query(value = "select c from Client c where c.client_number like :client_number")
    Optional<Client> findByClientNumber(@Param("client_number") String client_number);
    @Query(value = "select c from Client c where c.name like :name AND c.last_name = :last_name")
    Optional<Client> findByClientsAndLast_name(@Param("name") String name,@Param("last_name") String last_name);
    @Query(value = "select p from Patient p JOIN Client c on c.id_client=p.client.id_client")
    Optional<Patient> findByPatientId(@Param("id") int id_patient);

}
