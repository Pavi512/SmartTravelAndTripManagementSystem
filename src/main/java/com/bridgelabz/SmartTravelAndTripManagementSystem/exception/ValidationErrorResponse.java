package com.bridgelabz.SmartTravelAndTripManagementSystem.exception;

import lombok.Data;

import java.util.Map;

// Class used to return field validation errors
@Data
public class ValidationErrorResponse {

    // HTTP status code
    private int status;

    // Validation message
    private String message;

    // Stores field name and validation message
    private Map<String, String> errors;
}