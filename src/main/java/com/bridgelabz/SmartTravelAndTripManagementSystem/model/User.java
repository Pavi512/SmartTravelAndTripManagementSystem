package com.bridgelabz.SmartTravelAndTripManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

// Class is a JPA entity
@Entity

// Maps the entity to the users table in the database
@Table(name = "users")

// Generates getters, setters, toString, equals and hashCode
@Data
public class User {

    // Primary key
    @Id
    // Automatically generates ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name cannot be null
    @Column(nullable = false, length = 100)
    private String name;

    // Email must be unique and cannot be null
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    // Password cannot be null
    @Column(nullable = false, length = 16)
    private String password;

    // Phone number with length 20
    @Column(length = 20)
    private String phone;

    // User role cannot be null
    @Column(length = 50, nullable = false)
    private String role;
}