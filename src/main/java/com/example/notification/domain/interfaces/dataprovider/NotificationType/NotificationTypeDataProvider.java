package com.example.notification.domain.interfaces.dataprovider.NotificationType;

import com.example.notification.domain.entity.NotificationType;

import java.util.List;

public interface NotificationTypeDataProvider {

    void save(NotificationType notificationType);

    List<NotificationType> findAllNotificationTypes();
}
