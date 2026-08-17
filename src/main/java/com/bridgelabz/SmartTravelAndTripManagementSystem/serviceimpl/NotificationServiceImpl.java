package com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.NotificationResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.UserNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Notification;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.NotificationRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.UserRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    // 19. GET /api/users/{id}/notifications
    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {

        // Check whether the user exists
        userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User with ID " + userId + " not found"));

        // Get notifications belonging to the user
        List<Notification> notifications =
                notificationRepository.findByUserId(userId);

        // Convert entities to response DTOs
        List<NotificationResponseDTO> responseDTOList = new ArrayList<>();

        for (Notification notification : notifications) {
            responseDTOList.add(
                    new NotificationResponseDTO(notification)
            );
        }

        return responseDTOList;
    }
}