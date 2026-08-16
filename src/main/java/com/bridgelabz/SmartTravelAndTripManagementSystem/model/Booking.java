package com.bridgelabz.SmartTravelAndTripManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    // Primary key for the booking
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Date on which the booking was created
    @Column(nullable = false)
    private LocalDate bookingDate;

    // Number of people included in the booking
    @Column(nullable = false)
    private Integer numberOfPeople;

    // Total amount calculated for the booking
    @Column(nullable = false)
    private Double totalAmount;

    // Current status of the booking
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    // Many bookings can belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many bookings can be created for the same travel package
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private TravelPackage travelPackage;

    /*
     * One booking can have only one payment.
     *
     * mappedBy = "booking" means that the Payment entity
     * owns the relationship through its booking field.
     */
    @OneToOne(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Payment payment;

    // Default constructor required by JPA
    public Booking() {
    }

    // Getter and Setter for id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for bookingDate

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Getter and Setter for numberOfPeople

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    // Getter and Setter for totalAmount

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter and Setter for booking status

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // Getter and Setter for User

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and Setter for TravelPackage

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    // Getter and Setter for Payment

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}