package com.example.notification.services;

import com.example.notification.domain.entity.Category;
import com.example.notification.domain.interfaces.dataprovider.Category.CategoryDataProvider;
import com.example.notification.services.dto.CategoryDTO;
import com.example.notification.services.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryDataProvider categoryDataProvider;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllCategories() {
        // Arrange
        List<Category> mockEntities = Collections.singletonList(new Category());
        List<CategoryDTO> mockDtos = Collections.singletonList(new CategoryDTO());

        when(categoryDataProvider.findAllCategories()).thenReturn(mockEntities);
        when(categoryMapper.toDtoList(mockEntities)).thenReturn(mockDtos);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(categoryDataProvider, times(1)).findAllCategories();
        verify(categoryMapper, times(1)).toDtoList(mockEntities);
    }

    @Test
    void shouldThrowExceptionWhenDataProviderFails() {
        // Arrange
        when(categoryDataProvider.findAllCategories()).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> categoryService.getAllCategories());
        assertEquals("Database error", thrown.getMessage());
        verify(categoryDataProvider, times(1)).findAllCategories();
        verifyNoInteractions(categoryMapper);
    }
}
