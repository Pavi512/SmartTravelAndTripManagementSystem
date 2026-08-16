package com.bridgelabz.SmartTravelAndTripManagementSystem.repository;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for performing database operations on Booking entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Retrieves all bookings associated with a specific user.
     */
    List<Booking> findByUserId(Long userId);
}