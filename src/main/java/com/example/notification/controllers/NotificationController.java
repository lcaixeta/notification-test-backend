package com.example.notification.controllers;

import com.example.notification.payload.NotificationRequest;
import com.example.notification.services.NotificationService;
import com.example.notification.services.dto.NotificationDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody NotificationRequest request) throws BadRequestException {
        notificationService.createNotification(request);
        return ResponseEntity.ok("Notification created successfully!");
    }

    @GetMapping
    @RequestMapping("/history")
    public ResponseEntity<List<NotificationDTO>> notificationLogHistory() {
        List<NotificationDTO> response = notificationService.getLogHistory();
        return ResponseEntity.ok(response);
    }
}
