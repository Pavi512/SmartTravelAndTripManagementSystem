package com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.PaymentRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.PaymentResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Payment;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.PaymentStatus;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.BookingResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.BookingNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.TravelPackageNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.UserNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Booking;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.BookingStatus;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.TravelPackage;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.User;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.BookingRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.TravelPackageRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.UserRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.BookingService;
import org.springframework.stereotype.Service;

//import java.awt.print.Book;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation for managing booking operations.
 */
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TravelPackageRepository travelPackageRepository;
    private final PaymentRepository paymentRepository;

    /**
     * Constructor injection for required repositories.
     */
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            TravelPackageRepository travelPackageRepository,
            PaymentRepository paymentRepository) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.travelPackageRepository = travelPackageRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {


        User user=userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(()->new UserNotFoundException("User with Id "+bookingRequestDTO.getUserId()+" not found"));
        TravelPackage travelPackage=travelPackageRepository.findById(bookingRequestDTO.getTravelPackageId())
                .orElseThrow(()->new TravelPackageNotFoundException("Travel Package with ID "+bookingRequestDTO.getTravelPackageId()+" not found"));
        Booking booking=new Booking();
        booking.setBookingDate(bookingRequestDTO.getBookingDate());
        booking.setNumberOfPeople(bookingRequestDTO.getNumberOfPeople());
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);
        booking.setStatus(BookingStatus.PENDING);
        double totalAmt=travelPackage.getPrice()*bookingRequestDTO.getNumberOfPeople();
        booking.setTotalAmount(totalAmt);
        Booking savedBooking=bookingRepository.save(booking);

        return convertToResponseDTO(savedBooking);
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking=bookingRepository.findById(id).orElseThrow(()->new BookingNotFoundException("Booking with Id "+id+" not found"));
        return convertToResponseDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUserId(Long userId) {
//        Check whether the user exists
        userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with Id "+userId+" Not found"));
//        Fetch all Bookings belonging to the UUser
        List<Booking> bookings=bookingRepository.findByUserId(userId);

//        Convert each booking entity to BookingResponseDTO objects
        return bookings.stream().map(this::convertToResponseDTO).toList();
    }

    @Override
    public BookingResponseDTO cancelBooking(Long id) {

//        Find the booking by ID
        Booking booking=bookingRepository.findById(id).orElseThrow(
                ()->new BookingNotFoundException("Booking with Id "+id+" not found")
        );

//        change booking status to cancelled
        booking.setStatus(BookingStatus.CANCELLED);

//        Save the updated booking
        Booking updatedbooking=bookingRepository.save(booking);
        return convertToResponseDTO(updatedbooking);
    }
    /**
     * Converts a Booking entity into a BookingResponseDTO.
     */
    private BookingResponseDTO convertToResponseDTO(Booking booking) {

        BookingResponseDTO responseDTO = new BookingResponseDTO();

        responseDTO.setId(booking.getId());
        responseDTO.setBookingDate(booking.getBookingDate());
        responseDTO.setNumberOfPeople(booking.getNumberOfPeople());
        responseDTO.setTotalAmount(booking.getTotalAmount());
        responseDTO.setStatus(booking.getStatus());
        responseDTO.setUserId(booking.getUser().getId());
        responseDTO.setTravelPackageId(booking.getTravelPackage().getId());

        return responseDTO;
    }

    @Override
    @Transactional
    public PaymentResponseDTO makePayment(
            Long bookingId,
            PaymentRequestDTO paymentRequestDTO) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking with Id " + bookingId + " not found"
                        ));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalArgumentException(
                    "Payment cannot be made for a cancelled booking"
            );
        }

        if (paymentRepository.existsByBookingId(bookingId)) {
            throw new IllegalArgumentException(
                    "Payment already exists for booking Id " + bookingId
            );
        }

        if (!booking.getTotalAmount()
                .equals(paymentRequestDTO.getAmount())) {

            throw new IllegalArgumentException(
                    "Payment amount must be equal to booking total amount"
            );
        }

        Payment payment = new Payment();

        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setBooking(booking);

        Payment savedPayment = paymentRepository.save(payment);

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        PaymentResponseDTO responseDTO = new PaymentResponseDTO();

        responseDTO.setId(savedPayment.getId());
        responseDTO.setAmount(savedPayment.getAmount());
        responseDTO.setPaymentDate(savedPayment.getPaymentDate());
        responseDTO.setStatus(savedPayment.getStatus());
        responseDTO.setBookingId(booking.getId());

        return responseDTO;
    }
}