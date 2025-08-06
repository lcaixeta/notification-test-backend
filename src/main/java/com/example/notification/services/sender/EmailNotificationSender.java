package com.example.notification.services.sender;

import com.example.notification.domain.entity.Category;
import com.example.notification.domain.entity.User;
import org.springframework.stereotype.Component;

@Component("E-Mail")
public class EmailNotificationSender implements NotificationSender {
    @Override
    public void send(User user, String message, Category category) {
        System.out.printf("Sending Email to %s: %s%n", user.getEmail(), message);
    }
}


