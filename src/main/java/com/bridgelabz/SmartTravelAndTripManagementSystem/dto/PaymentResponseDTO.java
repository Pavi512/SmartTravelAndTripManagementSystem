package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.PaymentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentResponseDTO {

    // Payment ID
    private Long id;

    // Amount paid
    private Double amount;

    // Date of payment
    private LocalDate paymentDate;

    // Payment status
    private PaymentStatus status;

    // ID of the booking for which payment was made
    private Long bookingId;
}