package com.bridgelabz.SmartTravelAndTripManagementSystem.repository;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByTravelPackageId(Long travelPackageId);
}