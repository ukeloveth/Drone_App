package com.test.droneapp.service.impl;

import com.test.droneapp.config.Default_Messages;
import com.test.droneapp.dtos.request.DroneRegistrationPayload;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.entity.Medication;
import com.test.droneapp.enums.DroneModel;
import com.test.droneapp.enums.DroneState;
import com.test.droneapp.execptions.DroneException;
import com.test.droneapp.execptions.MedicationException;
import com.test.droneapp.repositories.DroneRepository;
import com.test.droneapp.repositories.MedicationRepository;
import com.test.droneapp.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DroneImpl implements DroneService {
    private DroneRepository droneRepository;
    private MedicationRepository medicationRepository;
    @Override
    public ResponseEntity<Drone> createADrone(DroneRegistrationPayload droneDto) {
        Drone drone = Drone.builder()
                .droneModel(droneDto.getModel())
                .serialNumber(droneDto.getSerialNumber())
                .batteryPercentage(100.0)
                .state(DroneState.IDLE)
                .build();
        Drone createdDroneEntity = droneRepository.save(drone);
        return new ResponseEntity<>(createdDroneEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Drone>> fetchAllDrones() {
        return  ResponseEntity.ok(droneRepository.findAll());
    }

    @Override
    public ResponseEntity<String> loadDroneWithAMedication (Long droneId, Long medicationId) {
        Drone droneEntity = findDroneByIdOrElseThrowException(droneId);
        Medication medication = findMedicationByIdOrElseThrowException(medicationId);

        if(medication.getDrone() != null) throw new MedicationException(Default_Messages.MEDICATION_ATTACHED_EXCEPTION);

        validateDroneAvailabilityOrElseThrowException(droneEntity, medication);
        droneEntity.setCurrentWeight(droneEntity.getCurrentWeight() + medication.getWeight());

        droneEntity.getMedicationList().add(medication);
        medication.setDrone(droneEntity);

        revaluateDroneState(droneEntity);
        droneRepository.save(droneEntity);
        return ResponseEntity.ok(String.format(Default_Messages.MEDICATION_LOAD_SUCCESS, medicationId, droneId));
    }

    @Override
    public ResponseEntity<List<Medication>> fetchMedicationsForADrone(Long droneId) {
        Drone droneEntity = findDroneByIdOrElseThrowException(droneId);
        return ResponseEntity.ok(droneEntity.getMedicationList());
    }
    @Override
    public ResponseEntity<List<Drone>> fetchAllAvailableDrones() {
        return ResponseEntity.ok(droneRepository.findDronesWhereStateIs(DroneState.IDLE, DroneState.LOADING));
    }

    @Override
    public ResponseEntity<String> fetchDroneBatteryLevel(Long droneId) {
        Drone droneEntity = findDroneByIdOrElseThrowException(droneId);
        String response = String.format(Default_Messages.DRONE_BATTERY_LEVEL, droneEntity.getBatteryPercentage());
        return ResponseEntity.ok(response);
    }



    private Drone findDroneByIdOrElseThrowException(Long droneId) {
        return droneRepository.findById(droneId)
                .orElseThrow(()->new DroneException(
                        String.format(Default_Messages.DRONE_NOT_FOUND, droneId)
                ));
    }

    private Medication findMedicationByIdOrElseThrowException(Long medicationId) {
        return medicationRepository.findById(medicationId)
                .orElseThrow(()-> new MedicationException(
                        String.format(Default_Messages.MEDICATION_NOT_FOUND, medicationId))
                );
    }




    private void validateDroneAvailabilityOrElseThrowException(Drone droneEntity, Medication medication) {
        DroneState droneState = droneEntity.getState();
        DroneModel droneModel = droneEntity.getDroneModel();

        long droneId = droneEntity.getId();
        double droneCapacity = droneModel.getWeight();
        double droneCurrentWeight = droneEntity.getCurrentWeight();

        if(!(droneState.equals(DroneState.IDLE) || droneState.equals(DroneState.LOADING))) {
            throw new DroneException(
                    String.format(Default_Messages.DRONE_NOT_AVAILABLE, droneId));
        }

        if(droneEntity.getBatteryPercentage() < 25) {
            throw new DroneException(
                    String.format(Default_Messages.DRONE_BATTERY_LOW, droneId)
            );
        }

        if (droneCapacity < medication.getWeight() + droneCurrentWeight){
            throw new DroneException(
                    String.format(Default_Messages.CAPACITY_EXCEEDED, droneCapacity, droneId, (droneCapacity - droneCurrentWeight))
            );
        }
    }

    private void revaluateDroneState(Drone droneEntity) {
        if(droneEntity.getCurrentWeight() < droneEntity.getDroneModel().getWeight()){
            droneEntity.setState(DroneState.LOADING);
        } else if(droneEntity.getCurrentWeight() == droneEntity.getDroneModel().getWeight()){
            droneEntity.setState(DroneState.LOADED);
        }
    }

}
