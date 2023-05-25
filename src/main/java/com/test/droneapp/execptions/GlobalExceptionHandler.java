package com.test.droneapp.execptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<ApiErrorDetail> createHttpResponse (HttpStatus httpStatus, String message) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(message, httpStatus.getReasonPhrase());
        ApiResponse errorDetails = new ApiResponse(errorMap, httpStatus);
        return new ResponseEntity(errorDetails, httpStatus);
    }


    @ExceptionHandler(DroneException.class)
    public ResponseEntity<?> handleDroneNotFoundEx(DroneException ex, WebRequest request){
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicationException.class)
    public ResponseEntity<?> handleMedicationNotFoundEx(MedicationException ex) {
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExcessWeightException.class)
    public ResponseEntity<?> excessWeightException(ExcessWeightException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(createHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }



}

