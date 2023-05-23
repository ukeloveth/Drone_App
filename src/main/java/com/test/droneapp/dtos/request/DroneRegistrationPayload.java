package com.test.droneapp.dtos.request;

import com.test.droneapp.entity.Drone;
import com.test.droneapp.enums.DroneModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegistrationPayload {
    private DroneModel model;
    private String serialNumber;
}
