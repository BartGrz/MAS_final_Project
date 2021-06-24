package com.pl.bg.javamasproject.demo.MP1;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;



public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {


       // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
       // Reception reception = context.getBean(Reception.class);


       // Pet pet =new Pet("kropka",Pet.convertFromEnum(Pet.Species.MAMMAL),2009);
       // System.out.println(pet);


           //  Client client = new Client("jan","janusz","602555444" ); //Optional.of("janjanusz@gmail")
           //  Client client_2 = new Client("Andrzej","Nowak","192554887");
          //   Client client_3 = new Client("Tomasz","Zawada","669112364");
           //  List<Client> list = Arrays.asList(client,client_2,client_3);
          // Looper.forLoop(0,list.size(),i -> logger.info(""+list.get(i)));




       // reception.addClientToSystem(client,new Pet("kropka", Pet.convertFromEnum(Pet.Species.MAMMAL),2020));
       //reception.addClientToSystem(client_2,new Pet("smok",Pet.convertFromEnum(Pet.Species.REPTILE),2017));
       // reception.addClientToSystem(client_3,new Pet("omaha",Pet.convertFromEnum(Pet.Species.AMPHIBIAN),2010));
     //   logger.info(""+Reception.showClientsFromSystem());
      //  logger.info(""+Reception.showPetsFromSystem());
      //  logger.info(""+Reception.findClientBy(1));
        logger.info(""+Reception.findClientBy("Tomasz"));
        logger.info(""+Reception.findClientBy(1));
        //reception.addPetToExistingClient(1,new Pet("gad",Pet.convertFromEnum(Pet.Species.REPTILE),2021));

        //logger.info(""+Reception.filterBySpecies(Pet.Species.REPTILE));
       // logger.info(""+reception.showClientsAndTheirOwnersById(1).get());



        //context.close();

    }

}
