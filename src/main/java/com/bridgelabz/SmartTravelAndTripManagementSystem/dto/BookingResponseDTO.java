package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.BookingStatus;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO used to send booking information to the client.
 */
@Data
public class BookingResponseDTO {

    // Unique ID of the booking
    private Long id;

    // Date on which the booking was made
    private LocalDate bookingDate;

    // Number of people included in the booking
    private Integer numberOfPeople;

    // Total amount calculated for the booking
    private Double totalAmount;

    // Current status of the booking
    private BookingStatus status;

    // ID of the user who created the booking
    private Long userId;

    // ID of the travel package associated with the booking
    private Long travelPackageId;
}