package com.example.notification.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private Long categoryId;
    private String message;
}
