package com.example.notification.services;

import com.example.notification.domain.interfaces.dataprovider.LogHistory.LogHistoryDataProvider;
import com.example.notification.services.dto.NotificationDTO;
import com.example.notification.services.mapper.LogHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final LogHistoryDataProvider logHistoryDataProvider;
    private final LogHistoryMapper mapper;

    @Autowired
    public NotificationService(LogHistoryDataProvider logHistoryDataProvider, LogHistoryMapper mapper) {
        this.logHistoryDataProvider = logHistoryDataProvider;
        this.mapper = mapper;
    }

    public List<NotificationDTO> getLogHistory() {
        return mapper.toDtoList(logHistoryDataProvider.findAllLogHistory());
    }
}
