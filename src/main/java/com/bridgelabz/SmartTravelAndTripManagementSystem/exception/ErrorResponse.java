package com.bridgelabz.SmartTravelAndTripManagementSystem.exception;

import lombok.Data;

import java.sql.Timestamp;

// Class used for error responses
@Data
public class ErrorResponse {

    // HTTP status code
    private int status;

    // Error message
    private String message;

    // Time when error occurred
    private Timestamp timestamp;
}