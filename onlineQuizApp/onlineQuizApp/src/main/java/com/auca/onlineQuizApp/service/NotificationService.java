package com.auca.onlineQuizApp.service;

import com.auca.onlineQuizApp.model.Notification;
import com.auca.onlineQuizApp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getUnreadNotifications() {
        return notificationRepository.findByIsReadFalse();
    }

    public void sendNotification(String title, String message) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    public void markAsRead(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.ifPresent(notif -> {
            notif.setRead(true);
            notificationRepository.save(notif);
        });
    }
    @Transactional
    public void markAllAsRead() {
        notificationRepository.markAllAsRead();
    }
}