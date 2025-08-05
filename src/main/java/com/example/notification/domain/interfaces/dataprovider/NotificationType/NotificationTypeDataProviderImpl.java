package com.example.notification.domain.interfaces.dataprovider.NotificationType;

import com.example.notification.domain.entity.NotificationType;
import com.example.notification.domain.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class NotificationTypeDataProviderImpl implements NotificationTypeDataProvider {

    private final NotificationTypeRepository repository;

    @Autowired
    public NotificationTypeDataProviderImpl(NotificationTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(NotificationType notificationType) {
        repository.saveAndFlush(notificationType);
    }

    @Override
    public List<NotificationType> findAllNotificationTypes() {
        return repository.findAll();
    }
}
