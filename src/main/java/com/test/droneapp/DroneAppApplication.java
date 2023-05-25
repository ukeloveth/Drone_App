package com.test.droneapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DroneAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneAppApplication.class, args);
    }

}
