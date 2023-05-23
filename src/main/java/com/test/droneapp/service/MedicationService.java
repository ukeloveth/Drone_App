package com.test.droneapp.service;

import com.test.droneapp.dtos.request.MedicationPayload;
import org.springframework.http.ResponseEntity;

public interface MedicationService {
    ResponseEntity<String> createMedication(MedicationPayload medicationPayload);
}
