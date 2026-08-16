package com.bridgelabz.SmartTravelAndTripManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    // Primary key for the payment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Amount paid by the user
    @Column(nullable = false)
    private Double amount;

    // Date on which payment was made
    @Column(nullable = false)
    private LocalDate paymentDate;

    // Current status of the payment
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    /*
     * One payment belongs to one booking.
     *
     * This is the owning side of the
     * Booking <-> Payment relationship.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "booking_id",
            nullable = false,
            unique = true
    )
    private Booking booking;

    // Default constructor required by JPA
    public Payment() {
    }

    // Getter and Setter for id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for amount

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // Getter and Setter for paymentDate

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Getter and Setter for status

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    // Getter and Setter for booking

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}