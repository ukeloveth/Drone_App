package com.test.droneapp.service.impl;

import com.test.droneapp.constants.Default_Messages;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
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
        Drone createdDrone = droneRepository.save(drone);
        return new ResponseEntity<>(createdDrone, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Drone>> fetchAllDrones() {
        return  ResponseEntity.ok(droneRepository.findAll());
    }

    @Override
    public ResponseEntity<String> loadDroneWithAMedication (Long droneId, Long medicationId) {
        Drone Drone = findDroneByIdOrElseThrowException(droneId);
        Medication medication = findMedicationByIdOrElseThrowException(medicationId);

        if(medication.getDrone() != null) throw new MedicationException(Default_Messages.MEDICATION_ATTACHED_EXCEPTION);

        validateDroneAvailabilityOrElseThrowException(Drone, medication);
        Drone.setCurrentWeight(Drone.getCurrentWeight() + medication.getWeight());

        Drone.getMedicationList().add(medication);
        medication.setDrone(Drone);

        revaluateDroneState(Drone);
        droneRepository.save(Drone);
        return ResponseEntity.ok(String.format(Default_Messages.MEDICATION_LOAD_SUCCESS, medicationId, droneId));
    }

    @Override
    public ResponseEntity<List<Medication>> fetchMedicationsForADrone(Long droneId) {
        Drone Drone = findDroneByIdOrElseThrowException(droneId);
        return ResponseEntity.ok(Drone.getMedicationList());
    }
    @Override
    public ResponseEntity<List<Drone>> fetchAllAvailableDrones() {
        return ResponseEntity.ok(droneRepository.findDronesWhereStateIs(DroneState.IDLE, DroneState.LOADING));
    }

    @Override
    public ResponseEntity<String> fetchDroneBatteryLevel(Long droneId) {
        Drone Drone = findDroneByIdOrElseThrowException(droneId);
        String response = String.format(Default_Messages.DRONE_BATTERY_LEVEL, Drone.getBatteryPercentage());
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


    private void validateDroneAvailabilityOrElseThrowException(Drone Drone, Medication medication) {
        DroneState droneState = Drone.getState();
        DroneModel droneModel = Drone.getDroneModel();

        long droneId = Drone.getId();
        double droneCapacity = droneModel.getWeight();
        double droneCurrentWeight = Drone.getCurrentWeight();

        if(!(droneState.equals(DroneState.IDLE) || droneState.equals(DroneState.LOADING))) {
            throw new DroneException(
                    String.format(Default_Messages.DRONE_NOT_AVAILABLE, droneId));
        }

        if(Drone.getBatteryPercentage() < 25) {
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

    private void revaluateDroneState(Drone Drone) {
        if(Drone.getCurrentWeight() < Drone.getDroneModel().getWeight()){
            Drone.setState(DroneState.LOADING);
        } else if(Drone.getCurrentWeight() == Drone.getDroneModel().getWeight()){
            Drone.setState(DroneState.LOADED);
        }
    }

}
