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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for NotificationService. All dependencies are mocked.
 */
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private LogHistoryDataProvider logHistoryDataProvider;

    @Mock
    private UserDataProvider userDataProvider;

    @Mock
    private CategoryDataProvider categoryDataProvider;

    @Mock
    private LogHistoryMapper logHistoryMapper;

    @Mock
    private NotificationSender smsSender;

    @Captor
    private ArgumentCaptor<List<LogHistory>> logsCaptor;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getLogHistory_shouldReturnMappedDtos() {
        // given
        List<LogHistory> domainLogs = List.of(LogHistory.builder().id(1L).build());
        List<NotificationDTO> dtoList = List.of(new NotificationDTO());

        when(logHistoryDataProvider.findAllByOrderBySentTimestampDesc()).thenReturn(domainLogs);
        when(logHistoryMapper.toDtoList(domainLogs)).thenReturn(dtoList);

        // when
        List<NotificationDTO> result = notificationService.getLogHistory();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(dtoList.get(0), result.get(0));

        verify(logHistoryDataProvider, times(1)).findAllByOrderBySentTimestampDesc();
        verify(logHistoryMapper, times(1)).toDtoList(domainLogs);
        verifyNoMoreInteractions(logHistoryDataProvider, logHistoryMapper);
    }

    @Test
    void createNotification_shouldSendAndPersistLogs_whenSenderExists() throws Exception {
        // given
        NotificationRequest request = new NotificationRequest();
        request.setCategoryId(10L);
        request.setMessage("Hello world");

        Category category = mock(Category.class);
        when(category.getId()).thenReturn(10L);
        when(categoryDataProvider.findById(10L)).thenReturn(category);

        // mock one user with one channel
        User user = mock(User.class);
        NotificationType channel = mock(NotificationType.class);
        when(channel.getName()).thenReturn("SMS");
        when(user.getChannels()).thenReturn(List.of(channel));
        when(userDataProvider.findAllByCategory(10L)).thenReturn(List.of(user));

        // create a senders map containing the smsSender mock
        Map<String, NotificationSender> senders = Map.of("SMS", smsSender);
        // inject the map into the service instance
        notificationService = new NotificationService(logHistoryDataProvider, userDataProvider, categoryDataProvider, logHistoryMapper, senders);

        // sender sends without exceptions
        doNothing().when(smsSender).send(user, request.getMessage(), category);

        // when
        assertDoesNotThrow(() -> notificationService.createNotification(request));

        // then
        // verify sender called
        verify(smsSender, times(1)).send(user, request.getMessage(), category);

        // verify saveAll called with one LogHistory
        verify(logHistoryDataProvider, times(1)).saveAll(logsCaptor.capture());
        List<LogHistory> persisted = logsCaptor.getValue();
        assertNotNull(persisted);
        assertEquals(1, persisted.size());
        LogHistory captured = persisted.get(0);
        assertEquals(user, captured.getUser());
        assertEquals(category, captured.getCategory());
        assertEquals(channel, captured.getNotificationType());
        assertEquals("Hello world", captured.getMessage());
    }

    @Test
    void createNotification_shouldSkipWhenNoSenderFoundAndNotPersist() {
        // given
        NotificationRequest request = new NotificationRequest();
        request.setCategoryId(20L);
        request.setMessage("No sender test");

        Category category = mock(Category.class);
        when(category.getId()).thenReturn(20L);
        when(categoryDataProvider.findById(20L)).thenReturn(category);

        User user = mock(User.class);
        NotificationType channel = mock(NotificationType.class);
        when(channel.getName()).thenReturn("UNKNOWNSENDER");
        when(user.getChannels()).thenReturn(List.of(channel));
        when(userDataProvider.findAllByCategory(20L)).thenReturn(List.of(user));

        // empty senders map
        Map<String, NotificationSender> senders = Map.of();
        notificationService = new NotificationService(logHistoryDataProvider, userDataProvider, categoryDataProvider, logHistoryMapper, senders);

        // when / then: should not throw
        assertDoesNotThrow(() -> notificationService.createNotification(request));

        // verify no save performed because no logs were created
        verify(logHistoryDataProvider, never()).saveAll(anyList());
    }

    @Test
    void createNotification_shouldPropagateRuntimeException_whenSenderFails() throws Exception {
        // given
        NotificationRequest request = new NotificationRequest();
        request.setCategoryId(30L);
        request.setMessage("Should fail");

        Category category = mock(Category.class);
        when(category.getId()).thenReturn(30L);
        when(categoryDataProvider.findById(30L)).thenReturn(category);

        User user = mock(User.class);
        NotificationType channel = mock(NotificationType.class);
        when(channel.getName()).thenReturn("SMS");
        when(user.getChannels()).thenReturn(List.of(channel));
        when(userDataProvider.findAllByCategory(30L)).thenReturn(List.of(user));

        Map<String, NotificationSender> senders = Map.of("SMS", smsSender);
        notificationService = new NotificationService(logHistoryDataProvider, userDataProvider, categoryDataProvider, logHistoryMapper, senders);

        // sender throws
        doThrow(new RuntimeException("boom")).when(smsSender).send(user, request.getMessage(), category);

        // when / then
        RuntimeException ex = assertThrows(RuntimeException.class, () -> notificationService.createNotification(request));
        assertTrue(ex.getMessage().contains("Error sending notification"));

        // ensure no persistence happened
        verify(logHistoryDataProvider, never()).saveAll(anyList());
    }

    @Test
    void createNotification_shouldThrowBadRequest_whenRequestIsNull() {
        assertThrows(BadRequestException.class, () -> notificationService.createNotification(null));
    }

    @Test
    void createNotification_shouldThrowBadRequest_whenCategoryIdIsNull() {
        NotificationRequest request = new NotificationRequest();
        request.setCategoryId(null);
        request.setMessage("msg");
        assertThrows(BadRequestException.class, () -> notificationService.createNotification(request));
    }

    @Test
    void createNotification_shouldThrowBadRequest_whenMessageIsBlank() {
        NotificationRequest request = new NotificationRequest();
        request.setCategoryId(1L);
        request.setMessage("   "); // blank
        assertThrows(BadRequestException.class, () -> notificationService.createNotification(request));
    }
}
