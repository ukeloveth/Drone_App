package com.test.droneapp.controller;

import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.service.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drone")
public class DispatchController {

    final private DroneService droneService;

    @GetMapping("/all")
    public ResponseEntity<List<Drone>> fetchAllDrone(){
        return droneService.fetchAllDrones();
    }

    @GetMapping("/medication")
    private ResponseEntity<List<Medication>> checkMedicationsForGivenDrone (@RequestParam("drone-id")Long droneId){
        return droneService.fetchMedicationsForADrone(droneId);
    }

    @GetMapping("/available")
    private ResponseEntity<List<Drone>> checkAllAvailableDrones (){
        return droneService.fetchAllAvailableDrones();
    }

    @GetMapping("/battery-level")
    private ResponseEntity<String> checkDroneBatteryLevel (@RequestParam("drone-id")Long droneId){
        return droneService.fetchDroneBatteryLevel(droneId);
    }

    @PostMapping
    public ResponseEntity<Drone> registerDrone (@RequestBody DroneRegistrationPayload droneDto){
        return droneService.createADrone(droneDto);
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadDroneWithMedication(@RequestParam("drone-id")Long droneId, @RequestParam("med-id") Long medicationId){
        return droneService.loadDroneWithAMedication(droneId,medicationId);
    }

}
