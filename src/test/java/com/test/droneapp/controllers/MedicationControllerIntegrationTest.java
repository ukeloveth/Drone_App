package com.test.droneapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.droneapp.controller.MedicationController;
import com.test.droneapp.dtos.request.MedicationPayload;
import com.test.droneapp.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicationController.class)
public class MedicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MedicationService medicationService;

    @Test
    public void testRegisterMedication() throws Exception {

        MedicationPayload medicationDto = new MedicationPayload();

        when(medicationService.createMedication(any(MedicationPayload.class))).thenReturn(ResponseEntity.status(201).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/medication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicationDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
