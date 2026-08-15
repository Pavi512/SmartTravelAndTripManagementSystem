package com.bridgelabz.SmartTravelAndTripManagementSystem.service;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserResponseDTO;

import java.util.List;

// Service interface
public interface UserService {

    // Registers a new user
    public UserResponseDTO registerUser(UserRequestDTO user);

    // Retrieves all users
    public List<UserResponseDTO> getAllUsers();

    // Retrieves a user using ID
    public UserResponseDTO getUserById(long id);

    // Updates an existing user using ID
    public UserResponseDTO updateUser(long id, UserRequestDTO user);

    // Deletes an existing user using ID
    public void deleteUser(long id);
}