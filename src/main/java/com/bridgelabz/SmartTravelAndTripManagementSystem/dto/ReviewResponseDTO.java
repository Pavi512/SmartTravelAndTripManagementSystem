package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Review;
import lombok.Data;

@Data
public class ReviewResponseDTO {

    private Long id;
    private Integer rating;
    private String comment;
    private Long userId;
    private Long travelPackageId;

    public ReviewResponseDTO(Review review) {

        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();

        if (review.getUser() != null) {
            this.userId = review.getUser().getId();
        }

        if (review.getTravelPackage() != null) {
            this.travelPackageId = review.getTravelPackage().getId();
        }
    }
}