package com.test.droneapp.execptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class DroneException extends RuntimeException{
    public DroneException(String message){
        super(message);
    }
}
