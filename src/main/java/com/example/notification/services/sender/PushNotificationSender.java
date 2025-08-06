package com.example.notification.services.sender;

import com.example.notification.domain.entity.Category;
import com.example.notification.domain.entity.User;
import org.springframework.stereotype.Component;

@Component("Push Notification")
public class PushNotificationSender implements NotificationSender {
    @Override
    public void send(User user, String message, Category category) {
        System.out.printf("Sending Push Notificaton to %s: %s%n", user.getName(), message);
    }
}

