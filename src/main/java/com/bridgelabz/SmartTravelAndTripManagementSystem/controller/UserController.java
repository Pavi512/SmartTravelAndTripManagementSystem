package com.bridgelabz.SmartTravelAndTripManagementSystem.controller;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.UserResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as REST controller
@RestController

// URL for all user APIs
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // Constructor injection for UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registers a new user, return 201 CREATED
    // @Valid performs validation on UserRequestDTO
    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.registerUser(userRequestDTO), HttpStatus.CREATED);
    }

    // Retrieves all users, returns 200 OK
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // Retrieves a single user using user ID, returns 200 OK
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    // Updates an existing user using user ID, returns 200 OK
    // @Valid validates the request data
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable long id,@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.updateUser(id, userRequestDTO), HttpStatus.OK);
    }

    // Deletes an existing user using user ID, returns 204 NO CONTENT
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}