package com.test.droneapp.dtos.request;

import com.test.droneapp.entity.Drone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegistrationPayload {
    private Drone model;
    private String serialNumber;
}
