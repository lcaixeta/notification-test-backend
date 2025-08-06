package com.example.notification.domain.interfaces.dataprovider.LogHistory;

import com.example.notification.domain.entity.LogHistory;

import java.util.List;

public interface LogHistoryDataProvider {

    void save(LogHistory logHistory);

    void saveAll(List<LogHistory> logHistories);

    List<LogHistory> findAllLogHistory();

    List<LogHistory> findAllByOrderBySentTimestampDesc();
}
