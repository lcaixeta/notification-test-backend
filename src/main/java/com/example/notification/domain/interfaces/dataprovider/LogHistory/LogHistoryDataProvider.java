package com.example.notification.domain.interfaces.dataprovider.LogHistory;

import com.example.notification.domain.entity.LogHistory;

import java.util.List;

public interface LogHistoryDataProvider {

    void save(LogHistory logHistory);

    List<LogHistory> findAllLogHistory();

    List<LogHistory> findAllByOrderBySentTimestampDesc();
}
