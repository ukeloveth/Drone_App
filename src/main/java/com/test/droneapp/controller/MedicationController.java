package com.test.droneapp.controller;

import com.test.droneapp.dtos.request.MedicationPayload;
import com.test.droneapp.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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



    @Operation(
            summary = "Register Medication",
            description = "Register a Medication And Save it  in the Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<?>registerMedication(@RequestBody MedicationPayload medicationDto){
        return medicationService.createMedication(medicationDto);
    }


}
