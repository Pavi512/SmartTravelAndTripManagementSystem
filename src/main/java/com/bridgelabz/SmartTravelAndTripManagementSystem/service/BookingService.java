package com.bridgelabz.SmartTravelAndTripManagementSystem.service;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingResponseDTO;

import java.util.List;

/**
 * Service interface for managing booking operations.
 */
public interface BookingService {

    // Creates a new booking
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);

    // Retrieves a booking using its ID
    BookingResponseDTO getBookingById(Long id);

    // Retrieves all bookings associated with a user
    List<BookingResponseDTO> getBookingsByUserId(Long userId);

    // Cancels an existing booking using its ID
    BookingResponseDTO cancelBooking(Long id);
}