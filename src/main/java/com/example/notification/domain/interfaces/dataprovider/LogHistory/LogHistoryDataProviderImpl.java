package com.example.notification.domain.interfaces.dataprovider.LogHistory;

import com.example.notification.domain.entity.LogHistory;
import com.example.notification.domain.repository.LogHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LogHistoryDataProviderImpl implements LogHistoryDataProvider{

    private final LogHistoryRepository repository;

    @Autowired
    public LogHistoryDataProviderImpl(LogHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(LogHistory logHistory) {
        repository.saveAndFlush(logHistory);
    }

    @Override
    public void saveAll(List<LogHistory> logHistories) {
        repository.saveAll(logHistories);
        repository.flush();
    }

    @Override
    public List<LogHistory> findAllLogHistory() {
        return repository.findAll();
    }

    @Override
    public List<LogHistory> findAllByOrderBySentTimestampDesc() {
        return repository.findAllByOrderBySentTimestampDesc();
    }
}
