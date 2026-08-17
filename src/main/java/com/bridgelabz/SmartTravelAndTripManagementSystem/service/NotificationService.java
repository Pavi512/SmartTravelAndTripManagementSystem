package com.bridgelabz.SmartTravelAndTripManagementSystem.service;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    // 19. Get all notifications for a user
    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);
}