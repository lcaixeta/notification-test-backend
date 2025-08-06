package com.example.notification.domain.interfaces.dataprovider.Category;

import com.example.notification.domain.entity.Category;
import com.example.notification.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoryDataProviderImpl implements CategoryDataProvider {

    private final CategoryRepository repository;

    @Autowired
    public CategoryDataProviderImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Category category) {
        repository.saveAndFlush(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category findById(Long id) {
        Category category = repository.findById(id).get();
        return category;
    }
}
