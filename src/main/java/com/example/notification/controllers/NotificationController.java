package com.example.notification.controllers;

import com.example.notification.services.NotificationService;
import com.example.notification.services.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    @RequestMapping("/create")
    public ResponseEntity<String> createNotification() {
        return ResponseEntity.ok("create");
    }

    @GetMapping
    @RequestMapping("/history")
    public ResponseEntity<List<NotificationDTO>> notificationLogHistory() {
        List<NotificationDTO> response = notificationService.getLogHistory();
        return ResponseEntity.ok(response);
    }
}
