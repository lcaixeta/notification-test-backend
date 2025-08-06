package com.example.notification.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NotificationTypeDTO {

    @JsonProperty("name")
    private String name;
}
