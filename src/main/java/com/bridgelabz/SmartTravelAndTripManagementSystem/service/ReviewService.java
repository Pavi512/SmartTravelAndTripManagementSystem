package com.bridgelabz.SmartTravelAndTripManagementSystem.service;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.ReviewRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    // Add a new review
    ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO);

    // Get all reviews for a travel package
    List<ReviewResponseDTO> getReviewsByPackageId(Long packageId);

    // Update an existing review
    ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO);
}