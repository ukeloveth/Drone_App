package com.test.droneapp.execptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private Map<String, String> data;
    private HttpStatus status;
    private boolean error = true;

    public ApiResponse(Map<String, String> data, HttpStatus status){
        this.data = data;
        this.status = status;
    }
}
