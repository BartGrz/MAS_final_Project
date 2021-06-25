package com.pl.bg.javamasproject.demo.model;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Client")
@ToString
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id_client;
    @Getter
    @Setter
    private String note;
    @Getter
    @Setter
    private String client_number; 
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Patient> patients;
    @OneToMany(mappedBy = "client")
    @Getter
    @Setter
    private Set<Visit> visits;

    public Client(String name, String last_name, String phone_number, String email_adress, String PESEL,@Nullable String note ) {
        super(name, last_name, phone_number, email_adress, PESEL);
        this.note=note;
    }

    public Client() {
    }
}
