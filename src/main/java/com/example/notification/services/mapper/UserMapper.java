package com.example.notification.services.mapper;

import com.example.notification.domain.entity.User;
import com.example.notification.services.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
}
