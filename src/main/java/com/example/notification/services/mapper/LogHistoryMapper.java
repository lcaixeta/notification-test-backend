package com.example.notification.services.mapper;

import com.example.notification.domain.entity.LogHistory;
import com.example.notification.domain.entity.User;
import com.example.notification.domain.entity.Category;
import com.example.notification.domain.entity.NotificationType;
import com.example.notification.services.dto.NotificationDTO;
import com.example.notification.services.dto.UserDTO;
import com.example.notification.services.dto.CategoryDTO;
import com.example.notification.services.dto.NotificationTypeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogHistoryMapper {

    NotificationDTO toDto(LogHistory logHistory);

    List<NotificationDTO> toDtoList(List<LogHistory> logHistories);

    UserDTO toUserDto(User user);

    CategoryDTO toCategoryDto(Category category);

    NotificationTypeDTO toNotificationTypeDto(NotificationType notificationType);
}
