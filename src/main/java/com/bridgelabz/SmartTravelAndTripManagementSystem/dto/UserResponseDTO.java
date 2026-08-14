package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.User;
import lombok.Data;

// DTO used to send user response data to the client
@Data
public class UserResponseDTO {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String role;

    // Converts User entity into UserResponseDTO
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }
}