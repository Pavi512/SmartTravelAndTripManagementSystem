package com.bridgelabz.SmartTravelAndTripManagementSystem.dto;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Notification;
import lombok.Data;

@Data
public class NotificationResponseDTO {

    private Long id;
    private String message;
    private String type;
    private String status;
    private Long userId;

    public NotificationResponseDTO(Notification notification) {

        this.id = notification.getId();
        this.message = notification.getMessage();
        this.type = notification.getType();
        this.status = notification.getStatus();

        if (notification.getUser() != null) {
            this.userId = notification.getUser().getId();
        }
    }
}