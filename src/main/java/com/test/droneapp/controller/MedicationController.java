package com.test.droneapp.controller;

import com.test.droneapp.dtos.request.MedicationPayload;
import com.test.droneapp.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/medication")
public class MedicationController {

    private MedicationService medicationService;

    @PostMapping
    public ResponseEntity<?>registerMedication(@RequestBody MedicationPayload medicationDto){
        return medicationService.createMedication(medicationDto);
    }


}
