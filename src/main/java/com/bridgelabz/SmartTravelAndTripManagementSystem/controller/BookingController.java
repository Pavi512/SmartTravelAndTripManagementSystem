package com.bridgelabz.SmartTravelAndTripManagementSystem.controller;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Creates a new booking.
     */
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(
            @Valid @RequestBody BookingRequestDTO bookingRequestDTO) {

        BookingResponseDTO responseDTO =
                bookingService.createBooking(bookingRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    /**
     * Retrieves a booking using its ID.
     */
    @GetMapping("/{id}")
    public  ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable
                                                          Long id    )
    {
        BookingResponseDTO responseDTO=bookingService.getBookingById(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /**
     * Retrieves all bookings associated with a specific user.
     */
    @GetMapping("/user/{userId}")
    public  ResponseEntity<List<BookingResponseDTO>> getBookingByUserId(@PathVariable Long userId)
    {
        List<BookingResponseDTO> bookings=bookingService.getBookingsByUserId(userId);
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }

    /**
     * Cancels an existing booking using its ID.
     */
    @PutMapping("/{id}/cancel")
    public  ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long id)
    {
        BookingResponseDTO bookingResponseDTO=bookingService.cancelBooking(id);
        return new ResponseEntity<>(bookingResponseDTO,HttpStatus.OK);

    }
}