package com.test.droneapp.service.impl;

import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.enums.DroneModel;
import com.test.droneapp.enums.DroneState;
import com.test.droneapp.repositories.DroneRepository;
import com.test.droneapp.repositories.MedicationRepository;
import com.test.droneapp.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DroneImpl implements DroneService {
    private DroneRepository droneRepository;
    private MedicationRepository medicationRepository;
    @Override
    public ResponseEntity<Drone> createADrone(DroneRegistrationPayload droneDto) {
        Drone droneEntity = Drone.builder()
                .droneModel(droneDto.getModel())
                .serialNumber(droneDto.getSerialNumber())
                .batteryPercentage(100.0)
                .state(DroneState.IDLE)
                .build();
        Drone createdDroneEntity = droneRepository.save(droneEntity);
        return new ResponseEntity<>(createdDroneEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Drone>> fetchAllDrones() {
        return  ResponseEntity.ok(droneRepository.findAll());
    }

    @Override
    public ResponseEntity<?> loadDroneWithMedication(Long droneId, Long medicationId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Medication>> fetchMedicationsForADrone(Long droneId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Drone>> fetchAllAvailableDrones() {
        return null;
    }

    @Override
    public ResponseEntity<String> fetchDroneBatteryLevel(Long droneId) {
        return null;
    }
}
