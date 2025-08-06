package com.example.notification.services;

import com.example.notification.domain.interfaces.dataprovider.Category.CategoryDataProvider;
import com.example.notification.services.dto.CategoryDTO;
import com.example.notification.services.dto.NotificationDTO;
import com.example.notification.services.mapper.CategoryMapper;
import com.example.notification.services.mapper.LogHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryDataProvider categoryDataProvider;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryDataProvider categoryDataProvider, CategoryMapper mapper) {
        this.categoryDataProvider = categoryDataProvider;
        this.mapper = mapper;
    }

    public List<CategoryDTO> getAllCategories() {
        return mapper.toDtoList(categoryDataProvider.findAllCategories());
    }
}
