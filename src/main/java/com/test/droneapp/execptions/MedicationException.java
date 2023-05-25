package com.test.droneapp.execptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class MedicationException extends RuntimeException{
    public MedicationException(String message){
        super(message);
    }
}
