package com.bridgelabz.SmartTravelAndTripManagementSystem.repository;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /*
     * Checks whether a payment already exists
     * for the given booking.
     *
     * Example:
     * bookingId = 1
     * return true  -> payment already exists
     * return false -> payment does not exist
     */
    boolean existsByBookingId(Long bookingId);
}