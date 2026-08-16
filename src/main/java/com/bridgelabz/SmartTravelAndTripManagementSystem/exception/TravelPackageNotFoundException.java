package com.bridgelabz.SmartTravelAndTripManagementSystem.exception;

/**
 * Exception thrown when a requested travel package does not exist.
 */
public class TravelPackageNotFoundException extends RuntimeException {

    public TravelPackageNotFoundException(String message) {
        super(message);
    }
}