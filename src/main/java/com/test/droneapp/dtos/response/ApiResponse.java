package com.test.droneapp.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private Map<String, String> data;
    private String message;
    private boolean error = true;

    public ApiResponse(Map<String, String> data, String message){
        this.data = data;
        this.message = message;
    }
}
