package com.auca.onlineQuizApp.controller;

import com.auca.onlineQuizApp.model.Notification;
import com.auca.onlineQuizApp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/unread")
    @ResponseBody
    public List<Notification> getUnreadNotifications() {
        return notificationService.getUnreadNotifications();
    }


    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> sendNotification(@RequestParam String title, @RequestParam String message) {
        notificationService.sendNotification(title, message);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/mark-as-read/{id}")
    @ResponseBody
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/user/mark-all-as-read")
    @ResponseBody
    public ResponseEntity<?> markAllNotificationsAsRead() {
        notificationService.markAllAsRead();
        return ResponseEntity.ok().build();
    }

}
