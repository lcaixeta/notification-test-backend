package com.example.notification.services.mapper;

import com.example.notification.domain.entity.Category;
import com.example.notification.services.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category category);
}
