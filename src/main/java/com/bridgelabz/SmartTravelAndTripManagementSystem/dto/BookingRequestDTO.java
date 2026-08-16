package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO used to receive booking creation data from the client.
 */
@Data
public class BookingRequestDTO {

    // Validates that a user ID is provided
    @NotNull(message = "User ID is required")
    private Long userId;

    // Validates that a travel package ID is provided
    @NotNull(message = "Travel package ID is required")
    private Long travelPackageId;

    // Validates that the booking date is provided and is not in the past
    @NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date cannot be in the past")
    private LocalDate bookingDate;

    // Validates that at least one person is included in the booking
    @NotNull(message = "Number of people is required")
    @Min(value = 1, message = "Number of people must be at least 1")
    private Integer numberOfPeople;
}