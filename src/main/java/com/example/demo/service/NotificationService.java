package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Notification;


/**
 * Notification Service Interface
 * @author SANG VO
 *
 */
public interface NotificationService {
	List<Notification> getAllNotifications();
	
	Notification saveNotification(Notification notification);
	
	Notification updateNotification(Notification notification);
	
	int checkExistingNotification(long id);
	
	Notification getNotificationById(long id);

	int deleteNotification(long id);
	
}
