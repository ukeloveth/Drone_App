package com.test.droneapp.execptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ExcessWeightException extends RuntimeException{

    public ExcessWeightException(String message){
        super(message);
    }
}
