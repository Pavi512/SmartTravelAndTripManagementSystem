package com.bridgelabz.SmartTravelAndTripManagementSystem.controller;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.NotificationResponseDTO;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 19. GET /api/users/{id}/notifications
    // Get all notifications for a user
    @GetMapping("/users/{id}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(
            @PathVariable Long id) {

        return new ResponseEntity<>(
                notificationService.getNotificationsByUserId(id),
                HttpStatus.OK
        );
    }
}