package com.test.droneapp.dummy_data;

import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.enums.DroneModel;
import com.test.droneapp.enums.DroneState;
import com.test.droneapp.repositories.DroneRepository;
import com.test.droneapp.repositories.MedicationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Slf4j
//@RequiredArgsConstructor
public record PrefillDatabase(DroneRepository droneRepository,
                              MedicationRepository medicationRepository) {

    @PostConstruct
    public void Create() {
        Drone drone1 = Drone.builder()
                .serialNumber("00100")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.LIGHT_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone2 = Drone.builder()
                .serialNumber("00101")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.LIGHT_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone3 = Drone.builder()
                .serialNumber("00102")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.LIGHT_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone4 = Drone.builder()
                .serialNumber("00103")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.MIDDLE_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone5 = Drone.builder()
                .serialNumber("00104")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.MIDDLE_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone6 = Drone.builder()
                .serialNumber("00105")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.MIDDLE_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone7 = Drone.builder()
                .serialNumber("00106")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.HEAVY_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone8 = Drone.builder()
                .serialNumber("00107")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.HEAVY_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone9 = Drone.builder()
                .serialNumber("00108")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.CRUISER_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();
        Drone drone10 = Drone.builder()
                .serialNumber("00109")
                .batteryPercentage(100.00)
                .droneModel(DroneModel.CRUISER_WEIGHT)
                .state(DroneState.IDLE)
                .currentWeight(0)
                .build();

        Medication med1 = Medication.builder()
                .name("panadol")
                .weight(2.00)
                .image("panadol.jpg")
                .code("MD-001")
                .build();
        Medication med2 = Medication.builder()
                .name("vitamin-c")
                .weight(1.50)
                .image("vitamin-c.jpg")
                .code("MD-002")
                .build();
        Medication med3 = Medication.builder()
                .name("procold")
                .weight(2.50)
                .image("procold.jpg")
                .code("MD-003")
                .build();
        Medication med4 = Medication.builder()
                .name("ampiclox")
                .weight(3.00)
                .image("ampiclox.jpg")
                .code("MD-004")
                .build();

        medicationRepository.saveAll(List.of(med1, med2, med3, med4));
        droneRepository.saveAll(List.of(drone1, drone2, drone3, drone4, drone5, drone6, drone7, drone8, drone9, drone10));
    }
}
