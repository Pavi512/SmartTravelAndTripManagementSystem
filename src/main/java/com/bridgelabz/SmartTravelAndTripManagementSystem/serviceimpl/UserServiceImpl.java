package com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.UserNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.User;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.UserRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Make class as service class
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registers a new user
    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        // Creates a new User
        User user = new User();

        // Copied data from request DTO to user
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        user.setRole(userRequestDTO.getRole());

        // Saves user
        User savedUser = userRepository.save(user);

        // Convert saved user to response DTO
        return new UserResponseDTO(savedUser);
    }

    // Retrieves all users
    @Override
    public List<UserResponseDTO> getAllUsers() {

        // List to store users
        List<User> users= userRepository.findAll();

        // List to store response DTOs
        List<UserResponseDTO> responseDTOList = new ArrayList<>();

        // Converts each User to UserResponseDTO
        for (User user : users){
            responseDTOList.add(new UserResponseDTO(user));
        }

        return responseDTOList;
    }

    // Retrieves a user using ID
    @Override
    public UserResponseDTO getUserById(long id) {

        // Finds user by ID or throws exception if user does not exist
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        return new UserResponseDTO(user);
    }

    // Updates an existing user
    @Override
    public UserResponseDTO updateUser(long id, UserRequestDTO userRequestDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        // Updates user fields with request dto
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhone(userRequestDTO.getPhone());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        // Updated user saved
        User update = userRepository.save(user);

        return new UserResponseDTO((update));
    }

    // Deletes an existing user
    @Override
    public void deleteUser(long id) {

        // Finds user by ID or throws exception if user does not exist
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with ID " + id + " not found"));

        // Deletes the user
        userRepository.delete(user);
    }
}