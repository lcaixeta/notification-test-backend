package com.example.notification.domain.interfaces.dataprovider.User;

import com.example.notification.domain.entity.User;

import java.util.List;

public interface UserDataProvider {

    void save(User user);

    List<User> findAllUsers();

    List<User> findAllByCategory(Long categoryId);

    List<User> findAllByNotificationType(Long notificationTypeId);
}
