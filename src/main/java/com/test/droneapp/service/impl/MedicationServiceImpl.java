package com.test.droneapp.service.impl;

import com.test.droneapp.constants.Default_Messages;
import com.test.droneapp.dtos.request.MedicationPayload;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.repositories.MedicationRepository;
import com.test.droneapp.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private MedicationRepository medicationRepository;
    @Override
    public ResponseEntity<String> createMedication(MedicationPayload medicationPayload) {

        Medication medication = Medication.builder()
                .name(medicationPayload.getName())
                .code(medicationPayload.getCode())
                .weight(medicationPayload.getWeight())
                .image(medicationPayload.getImage())
                .build();
                medicationRepository.save(medication);
        return ResponseEntity.ok(String.format(Default_Messages.MEDICATION_CREATED_SUCCESS, medication.getName()));
    }
}
