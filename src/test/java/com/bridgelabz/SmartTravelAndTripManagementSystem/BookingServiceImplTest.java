package com.bridgelabz.SmartTravelAndTripManagementSystem;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.BookingNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.BookingStatus;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;


    // Test whether booking is created correctly
    @Test
    public void createBooking() {

        BookingRequestDTO request = new BookingRequestDTO();

        // Existing user and travel package
        request.setUserId(1L);
        request.setTravelPackageId(1L);

        request.setBookingDate(LocalDate.now().plusDays(10));
        request.setNumberOfPeople(2);

        BookingResponseDTO saved = bookingService.createBooking(request);

        assertNotNull(saved);
        assertNotNull(saved.getId());

        assertEquals(1L, saved.getUserId());
        assertEquals(1L, saved.getTravelPackageId());
        assertEquals(2, saved.getNumberOfPeople());
        assertEquals(30000.0, saved.getTotalAmount());
        assertEquals(BookingStatus.PENDING, saved.getStatus());
    }


    // Test whether booking is retrieved correctly using ID
    @Test
    public void getBookingById() {

        BookingRequestDTO request = new BookingRequestDTO();

        request.setUserId(1L);
        request.setTravelPackageId(1L);
        request.setBookingDate(LocalDate.now().plusDays(15));
        request.setNumberOfPeople(2);

        BookingResponseDTO saved = bookingService.createBooking(request);

        BookingResponseDTO found =
                bookingService.getBookingById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals(1L, found.getUserId());
        assertEquals(1L, found.getTravelPackageId());
        assertEquals(2, found.getNumberOfPeople());
    }


    // Test whether bookings are retrieved correctly using user ID
    @Test
    public void getBookingsByUserId() {

        BookingRequestDTO request = new BookingRequestDTO();

        request.setUserId(1L);
        request.setTravelPackageId(1L);
        request.setBookingDate(LocalDate.now().plusDays(20));
        request.setNumberOfPeople(1);

        bookingService.createBooking(request);

        List<BookingResponseDTO> bookings =
                bookingService.getBookingsByUserId(1L);

        assertNotNull(bookings);
        assertFalse(bookings.isEmpty());

        assertTrue(
                bookings.stream()
                        .allMatch(booking -> booking.getUserId().equals(1L))
        );
    }


    // Test whether booking is cancelled correctly
    @Test
    public void cancelBooking() {

        BookingRequestDTO request = new BookingRequestDTO();

        request.setUserId(1L);
        request.setTravelPackageId(1L);
        request.setBookingDate(LocalDate.now().plusDays(25));
        request.setNumberOfPeople(2);

        BookingResponseDTO saved =
                bookingService.createBooking(request);

        BookingResponseDTO cancelled =
                bookingService.cancelBooking(saved.getId());

        assertNotNull(cancelled);
        assertEquals(saved.getId(), cancelled.getId());
        assertEquals(BookingStatus.CANCELLED, cancelled.getStatus());
    }


    // Test whether exception is thrown for invalid booking ID
    @Test
    public void getBookingByInvalidId() {

        assertThrows(
                BookingNotFoundException.class,
                () -> bookingService.getBookingById(999999L)
        );
    }
}