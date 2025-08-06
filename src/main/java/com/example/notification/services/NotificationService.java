package com.example.notification.services;

import com.example.notification.domain.entity.Category;
import com.example.notification.domain.entity.LogHistory;
import com.example.notification.domain.entity.NotificationType;
import com.example.notification.domain.entity.User;
import com.example.notification.domain.interfaces.dataprovider.Category.CategoryDataProvider;
import com.example.notification.domain.interfaces.dataprovider.LogHistory.LogHistoryDataProvider;
import com.example.notification.domain.interfaces.dataprovider.User.UserDataProvider;
import com.example.notification.payload.NotificationRequest;
import com.example.notification.services.dto.NotificationDTO;
import com.example.notification.services.mapper.LogHistoryMapper;
import com.example.notification.services.sender.NotificationSender;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NotificationService {

    private final LogHistoryDataProvider logHistoryDataProvider;
    private final UserDataProvider userDataProvider;
    private final CategoryDataProvider categoryDataProvider;
    private final LogHistoryMapper mapper;
    private Map<String, NotificationSender> senders;
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    public NotificationService(LogHistoryDataProvider logHistoryDataProvider,
                               UserDataProvider userDataProvider,
                               CategoryDataProvider categoryDataProvider,
                               LogHistoryMapper mapper, Map<String, NotificationSender> senders) {
        this.logHistoryDataProvider = logHistoryDataProvider;
        this.userDataProvider = userDataProvider;
        this.categoryDataProvider = categoryDataProvider;
        this.mapper = mapper;
        this.senders = senders;
    }

    public List<NotificationDTO> getLogHistory() {
        return mapper.toDtoList(logHistoryDataProvider.findAllByOrderBySentTimestampDesc());
    }

    public void createNotification(NotificationRequest request) throws BadRequestException {
        validateRequest(request);

        Category category = categoryDataProvider.findById(request.getCategoryId());
        List<User> userList = userDataProvider.findAllByCategory(category.getId());

        List<LogHistory> logsToPersist = new ArrayList<>();

        userList.stream()
                .flatMap(user -> user.getChannels().stream()
                        .map(channel -> new AbstractMap.SimpleEntry<>(user, channel)))
                .forEach(entry -> {
                    User user = entry.getKey();
                    NotificationType channel = entry.getValue();

                    NotificationSender sender = senders.get(channel.getName());
                    if (sender != null) {
                        try {
                            sender.send(user, request.getMessage(), category);
                            LogHistory log = buildLogHistory(user, category, channel, request.getMessage());
                            logsToPersist.add(log);

                        } catch (Exception e) {
                            throw new RuntimeException(String.format("Error sending notification: %s", e));
                        }
                    } else {
                        log.warn("No sender found for channel {}", channel.getName());
                    }
                });

        if (!logsToPersist.isEmpty()) {
            logHistoryDataProvider.saveAll(logsToPersist);
        }

//        List<LogHistory> logs = userDataProvider.findAllByCategory(category.getId())
//                .stream()
//                .flatMap(user -> user.getChannels()
//                        .stream()
//                        .map(channel -> buildLogHistory(user, category, channel, request.getMessage())))
//                .collect(Collectors.toList());
//
//        if (!logs.isEmpty()) {
//            logHistoryDataProvider.saveAll(logs);
//        }
    }

    private void validateRequest(NotificationRequest request) throws BadRequestException {
        if (request == null) {
            throw new BadRequestException("Notification cannot be null.");
        }
        if (request.getCategoryId() == null) {
            throw new BadRequestException("Category is required!");
        }
        if (request.getMessage() == null || request.getMessage().isBlank()) {
            throw new BadRequestException("Message is required!");
        }
    }

    private LogHistory buildLogHistory(User user, Category category, NotificationType channel, String message) {
        return LogHistory.builder()
                .user(user)
                .category(category)
                .notificationType(channel)
                .message(message)
                .sentTimestamp(LocalDateTime.now())
                .build();
    }
}
