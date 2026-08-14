package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

// DTO used to receive user registration and update data
@Data
public class UserRequestDTO {

    // Validates that name is not null, empty or blank
    @NotBlank(message = "Name is required")
    private String name;

    // Validates that email is not empty and follows email format
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    // Validates that password is not empty and follows password rules
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$&*+]).{8,16}$",
            message = "Password must contain 8-16 characters, at least 1 uppercase, 1 lowercase, 1 digit and 1 special character (@#$&*+)")
    private String password;

    // Validates Indian mobile number format when phone is provided
    @Pattern(regexp = "^[6-9][0-9]{9}$",
            message = "Invalid phone number")
    private String phone;

    // Validates that role not null, empty or blank
    @NotBlank(message = "Role is required")
    private String role;
}