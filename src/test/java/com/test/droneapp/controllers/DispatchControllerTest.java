package com.test.droneapp.controllers;

import com.test.droneapp.controller.DispatchController;
import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.enums.DroneModel;
import com.test.droneapp.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DispatchControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DispatchController dispatchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAllDrones() {
        List<Drone> droneList = new ArrayList<>();
        // Add test drones to the list

        when(droneService.fetchAllDrones()).thenReturn(new ResponseEntity<>(droneList, HttpStatus.OK));

        ResponseEntity<List<Drone>> response = dispatchController.fetchAllDrones();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(droneList, response.getBody());
    }

    @Test
    public void testFetchMedicationsForADrone() {
        Long droneId = 1L;
        Drone drone = new Drone();
        // Set up test drone object

        List<Medication> medicationList = new ArrayList<>();
        // Add test medications to the list

        when(droneService.fetchMedicationsForADrone(droneId)).thenReturn(new ResponseEntity<>(medicationList, HttpStatus.OK));

        ResponseEntity<List<Medication>> response = dispatchController.checkMedicationsForAGivenDrone(droneId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicationList, response.getBody());
    }

    @Test
    public void testFetchAllAvailableDrones() {
        List<Drone> droneList = new ArrayList<>();
        // Add test drones to the list

        when(droneService.fetchAllAvailableDrones()).thenReturn(new ResponseEntity<>(droneList, HttpStatus.OK));

        ResponseEntity<List<Drone>> response = dispatchController.checkAllAvailableDrones();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(droneList, response.getBody());
    }

    @Test
    public void testCheckDroneBatteryLevel() {
        Long droneId = 1L;
        String expectedResponse = "Battery level: 80%";

        when(droneService.fetchDroneBatteryLevel(droneId)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        ResponseEntity<String> response = dispatchController.checkDroneBatteryLevel(droneId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testRegisterADrone() {
        DroneRegistrationPayload droneDto = new DroneRegistrationPayload(DroneModel.LIGHT_WEIGHT, "123456");

        Drone createdDrone = new Drone();
        // Set up the created drone object

        when(droneService.createADrone(droneDto)).thenReturn(new ResponseEntity<>(createdDrone, HttpStatus.CREATED));

        ResponseEntity<Drone> response = dispatchController.registerDrone(droneDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDrone, response.getBody());
    }

    @Test
    public void testLoadDroneWithAMedication() {
        Long droneId = 1L;
        Long medicationId = 1L;
        String expectedResponse = "Medication loaded successfully";

        when(droneService.loadDroneWithAMedication(droneId, medicationId)).thenReturn(new ResponseEntity(expectedResponse, HttpStatus.OK));

        ResponseEntity<?> response = dispatchController.loadDroneWithAMedication(droneId, medicationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}

