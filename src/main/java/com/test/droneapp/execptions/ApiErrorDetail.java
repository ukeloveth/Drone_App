package com.test.droneapp.execptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ApiErrorDetail {
    private Date timestamp;
    private String message;
    private HttpStatus httpStatus;

    public ApiErrorDetail(String message, HttpStatus httpStatus) {
        this.timestamp = new Date();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
