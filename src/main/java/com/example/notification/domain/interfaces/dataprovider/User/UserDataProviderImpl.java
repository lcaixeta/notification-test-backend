package com.example.notification.domain.interfaces.dataprovider.User;

import com.example.notification.domain.entity.User;
import com.example.notification.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDataProviderImpl implements UserDataProvider {

    private final UserRepository repository;

    @Autowired
    public UserDataProviderImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllByCategory(Long categoryId) {
        return repository.findAllByCategory(categoryId);
    }

    @Override
    public List<User> findAllByNotificationType(Long notificationTypeId) {
        return repository.findAllByNotificationType(notificationTypeId);
    }
}
