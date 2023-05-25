package com.test.droneapp.controllers;

import com.test.droneapp.controller.MedicationController;
import com.test.droneapp.dtos.request.MedicationPayload;
import com.test.droneapp.service.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MedicationControllerTest {

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private MedicationController medicationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterMedication() {


        MedicationPayload medicationDto = new MedicationPayload();

        ResponseEntity<?> responseEntity = new ResponseEntity<>("Medication created", HttpStatus.CREATED);

        when(medicationService.createMedication(any(MedicationPayload.class))).thenReturn((ResponseEntity<String>) responseEntity);


        ResponseEntity<?> result = medicationController.registerMedication(medicationDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Medication created", result.getBody());
    }
}
