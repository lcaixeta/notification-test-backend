package com.example.notification.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDTO {
    @JsonProperty("name")
    private String name;
}
