package com.example.notification.controllers;

import com.example.notification.payload.NotificationRequest;
import com.example.notification.services.CategoryService;
import com.example.notification.services.NotificationService;
import com.example.notification.services.dto.CategoryDTO;
import com.example.notification.services.dto.NotificationDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> response = categoryService.getAllCategories();
        return ResponseEntity.ok(response);
    }
}
