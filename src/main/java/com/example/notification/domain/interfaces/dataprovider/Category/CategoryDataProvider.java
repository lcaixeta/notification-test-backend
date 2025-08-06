package com.example.notification.domain.interfaces.dataprovider.Category;

import com.example.notification.domain.entity.Category;

import java.util.List;

public interface CategoryDataProvider {

    void save(Category category);

    List<Category> findAllCategories();

    Category findById(Long id);
}
