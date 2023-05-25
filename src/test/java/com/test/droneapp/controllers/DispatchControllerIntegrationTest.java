package com.test.droneapp.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.droneapp.controller.DispatchController;
import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.enums.DroneModel;
import com.test.droneapp.service.DroneService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DispatchController.class)
public class DispatchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DroneService droneService;

    @Test
    public void testFetchAllDrones() throws Exception {
        Drone drone1 = new Drone();
        Drone drone2 = new Drone();
        List<Drone> droneList = Arrays.asList(drone1, drone2);

        when(droneService.fetchAllDrones()).thenReturn(ResponseEntity.ok(droneList));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/drone/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(droneList.size()));
    }

    @Test
    public void testCheckMedicationsForAGivenDrone() throws Exception {
        Long droneId = 1L;
        Medication medication1 = new Medication();
        Medication medication2 = new Medication();
        List<Medication> medicationList = Arrays.asList(medication1, medication2);

        when(droneService.fetchMedicationsForADrone(droneId)).thenReturn(ResponseEntity.ok(medicationList));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/drone/medication")
                        .param("drone-id", String.valueOf(droneId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(medicationList.size()));
    }

    @Test
    public void testCheckAllAvailableDrones() throws Exception {
        Drone drone1 = new Drone();
        Drone drone2 = new Drone();
        List<Drone> droneList = Arrays.asList(drone1, drone2);

        when(droneService.fetchAllAvailableDrones()).thenReturn(ResponseEntity.ok(droneList));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/drone/available"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(droneList.size()));
    }

    @Test
    public void testCheckDroneBatteryLevel() throws Exception {
        Long droneId = 1L;
        String batteryLevelResponse = "Battery level: 80%";

        when(droneService.fetchDroneBatteryLevel(droneId)).thenReturn(ResponseEntity.ok(batteryLevelResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/drone/battery-level")
                        .param("drone-id", String.valueOf(droneId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(batteryLevelResponse));
    }

    @Test
    public void testRegisterADrone() throws Exception {
        DroneRegistrationPayload droneDto = new DroneRegistrationPayload(DroneModel.LIGHT_WEIGHT, "123456");
        Drone createdDrone = new Drone();

        when(droneService.createADrone(droneDto)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(createdDrone));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/drone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(droneDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }


    @Test
    public void testLoadDroneWithAMedication() throws Exception {
        Long droneId = 1L;
        Long medicationId = 1L;
        String loadMedicationResponse = "Medication loaded successfully";

        when(droneService.loadDroneWithAMedication(droneId, medicationId)).thenReturn(new ResponseEntity(loadMedicationResponse, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/drone/load")
                        .param("drone-id", String.valueOf(droneId))
                        .param("med-id", String.valueOf(medicationId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(loadMedicationResponse));
    }
}
