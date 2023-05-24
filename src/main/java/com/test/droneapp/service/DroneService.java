package com.test.droneapp.service;

import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {

    ResponseEntity<Drone> createADrone(DroneRegistrationPayload droneDto);
    ResponseEntity<List<Drone>> fetchAllDrones();
    ResponseEntity<?> loadDroneWithAMedication(Long droneId, Long medicationId);
    ResponseEntity<List<Medication>> fetchMedicationsForADrone(Long droneId);
    ResponseEntity<List<Drone>> fetchAllAvailableDrones();
    ResponseEntity<String> fetchDroneBatteryLevel(Long droneId);
}
