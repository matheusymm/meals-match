package com.ufscar.pooa.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.pooa.backend.dto.Notification.NotificationDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.INotificationService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NotificationDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No notifications found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content), })
    public ResponseEntity<List<NotificationDetailDTO>> getAllNotifications() {
        List<NotificationDetailDTO> notifications = notificationService.getAllNotifications();

        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(notifications);
    }
}
