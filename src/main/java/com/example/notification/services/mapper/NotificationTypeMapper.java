package com.example.notification.services.mapper;

import com.example.notification.domain.entity.NotificationType;
import com.example.notification.services.dto.NotificationTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationTypeMapper {
    NotificationTypeDTO toDto(NotificationType notificationType);
}
