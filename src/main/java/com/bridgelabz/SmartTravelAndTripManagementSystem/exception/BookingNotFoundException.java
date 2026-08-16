package com.bridgelabz.SmartTravelAndTripManagementSystem.exception;

/**
 * Exception thrown when a requested booking does not exist.
 */
public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(String message) {
        super(message);
    }
}