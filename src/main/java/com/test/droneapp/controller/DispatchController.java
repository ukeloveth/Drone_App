package com.test.droneapp.controller;

import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Drone Dispatch REST API",
        description = "This REST API allows clients to communicate with the drones. The drone is able to make deliveries to locations that are inaccessible. The application is developed to perform the following tasks"
)

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drone")
public class DispatchController {

    final private DroneService droneService;


    @Operation(
            summary = "Fetch all Drones",
            description = "Fetch All Drones in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESSFUL"
    )
    @GetMapping("/all")
    public ResponseEntity<List<Drone>> fetchAllDrones(){
        return droneService.fetchAllDrones();
    }



    @Operation(
            summary = "Check For A Medication For A Given Drone",
            description = "Check For A Medication For A Given Drone in Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESSFUL"
    )
    @GetMapping("/medication")
    public ResponseEntity<List<Medication>> checkMedicationsForAGivenDrone (@RequestParam("drone-id")Long droneId){
        return droneService.fetchMedicationsForADrone(droneId);
    }


    @Operation(
            summary = "Check All Available Drones",
            description = "Check All Available Drones in the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESSFUL"
    )
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> checkAllAvailableDrones(){
        return droneService.fetchAllAvailableDrones();
    }



    @Operation(
            summary = "Check Drone Battery Level",
            description = "Check Drone Battery Level in the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESSFUL"
    )
    @GetMapping("/battery-level")
    public ResponseEntity<String> checkDroneBatteryLevel (@RequestParam("drone-id")Long droneId){
        return droneService.fetchDroneBatteryLevel(droneId);
    }


    @Operation(
            summary = "Register a Drone",
            description = "Register a Drone And Save it  in the Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<Drone> registerDrone (@RequestBody DroneRegistrationPayload droneDto){
        return droneService.createADrone(droneDto);
    }


    @Operation(
            summary = "Load Drone With A Medication",
            description = "Load Drone With A Medication"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/load")
    public ResponseEntity<?> loadDroneWithAMedication(@RequestParam("drone-id")Long droneId, @RequestParam("med-id") Long medicationId){
        return droneService.loadDroneWithAMedication(droneId,medicationId);
    }

}
