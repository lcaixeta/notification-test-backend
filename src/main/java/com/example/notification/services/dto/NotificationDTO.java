package com.example.notification.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("user")
    private UserDTO user;
    @JsonProperty("category")
    private CategoryDTO category;
    @JsonProperty("notificationType")
    private NotificationTypeDTO notificationType;
    @JsonProperty("message")
    private String message;
    @JsonProperty("sentTimestamp")
    private LocalDateTime sentTimestamp;
}
