package com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.ReviewRequestDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.ReviewResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.UserNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Review;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.TravelPackage;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.User;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.ReviewRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.TravelPackageRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.UserRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TravelPackageRepository travelPackageRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             UserRepository userRepository,
                             TravelPackageRepository travelPackageRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.travelPackageRepository = travelPackageRepository;
    }

    // 16. POST /api/reviews
    @Override
    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO) {

        User user = userRepository.findById(reviewRequestDTO.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User with ID " + reviewRequestDTO.getUserId() + " not found"));

        TravelPackage travelPackage = travelPackageRepository
                .findById(reviewRequestDTO.getTravelPackageId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Travel Package with ID "
                                        + reviewRequestDTO.getTravelPackageId()
                                        + " not found"));

        Review review = new Review();

        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());
        review.setUser(user);
        review.setTravelPackage(travelPackage);

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDTO(savedReview);
    }

    // 17. GET /api/packages/{id}/reviews
    @Override
    public List<ReviewResponseDTO> getReviewsByPackageId(Long packageId) {

        List<Review> reviews =
                reviewRepository.findByTravelPackageId(packageId);

        List<ReviewResponseDTO> responseDTOList = new ArrayList<>();

        for (Review review : reviews) {
            responseDTOList.add(new ReviewResponseDTO(review));
        }

        return responseDTOList;
    }

    // 18. PUT /api/reviews/{id}
    @Override
    public ReviewResponseDTO updateReview(Long reviewId,
                                          ReviewRequestDTO reviewRequestDTO) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Review with ID " + reviewId + " not found"));

        User user = userRepository.findById(reviewRequestDTO.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User with ID " + reviewRequestDTO.getUserId() + " not found"));

        TravelPackage travelPackage = travelPackageRepository
                .findById(reviewRequestDTO.getTravelPackageId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Travel Package with ID "
                                        + reviewRequestDTO.getTravelPackageId()
                                        + " not found"));

        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());
        review.setUser(user);
        review.setTravelPackage(travelPackage);

        Review updatedReview = reviewRepository.save(review);

        return new ReviewResponseDTO(updatedReview);
    }
}