package com.pl.bg.javamasproject.demo;



import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * First javaFx thread must be started, then in springBootJavaFxApplication springBoot app is launched
 */
@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {

        Application.launch(SpringbootJavaFxApplication.class, args);

    }



}

