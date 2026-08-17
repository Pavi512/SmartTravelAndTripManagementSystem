package com.bridgelabz.SmartTravelAndTripManagementSystem.controller;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.ReviewRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.ReviewResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 16. POST /api/reviews
    // Add a new review
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDTO> addReview(
            @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {

        return new ResponseEntity<>(
                reviewService.addReview(reviewRequestDTO),
                HttpStatus.CREATED
        );
    }

    // 17. GET /api/packages/{id}/reviews
    // Get all reviews for a travel package
    @GetMapping("/packages/{id}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getPackageReviews(
            @PathVariable Long id) {

        return new ResponseEntity<>(
                reviewService.getReviewsByPackageId(id),
                HttpStatus.OK
        );
    }

    // 18. PUT /api/reviews/{id}
    // Update an existing review
    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {

        return new ResponseEntity<>(
                reviewService.updateReview(id, reviewRequestDTO),
                HttpStatus.OK
        );
    }
}