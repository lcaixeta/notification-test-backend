package com.example.notification.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
