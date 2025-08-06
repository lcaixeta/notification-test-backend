package com.example.notification.services.sender;

import com.example.notification.domain.entity.Category;
import com.example.notification.domain.entity.User;

public interface NotificationSender {
    void send(User user, String message, Category category) throws Exception;
}
