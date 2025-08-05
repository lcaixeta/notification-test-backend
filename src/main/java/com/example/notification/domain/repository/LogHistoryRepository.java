package com.example.notification.domain.repository;

import com.example.notification.domain.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogHistoryRepository extends JpaRepository<LogHistory, Long> {

    List<LogHistory> findAllByOrderBySentTimestampDesc();
}
