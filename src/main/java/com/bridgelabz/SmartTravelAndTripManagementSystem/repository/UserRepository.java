package com.bridgelabz.SmartTravelAndTripManagementSystem.repository;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Used for database operations on User
public interface UserRepository extends JpaRepository<User, Long> {
}