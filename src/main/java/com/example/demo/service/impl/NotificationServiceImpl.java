package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.NotificationService;

/**
 * Notification Service Implement Class
 * @author SANG VO
 */
@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationRepository notificationRepository;
	
	

	@Override
	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification saveNotification(Notification notification) {
		try {
			// Check existing data
			if (notification.getId() != 0 && checkExistingNotification(notification.getId()) == 1) {
				return null;
			}
			
			//  Insert data
			return notificationRepository.save(notification);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Notification updateNotification(Notification Notification) {
		try {
			Notification objOldNotification = notificationRepository.getById(Notification.getId());
			
			// Setting new data to old data
			objOldNotification.setId(Notification.getId());
			objOldNotification.setTitle(Notification.getTitle());
			objOldNotification.setType(Notification.getType());
			objOldNotification.setDescription(Notification.getDescription());
			
			return notificationRepository.save(objOldNotification);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * Notification check
	 */
	@Override
	public int checkExistingNotification(long id) {
		// TODO Auto-generated method stub
		int iResult = -1;
		Notification Notification;
		try {
			Notification = notificationRepository.findById(id).get();
			if (Notification.getId() < 1) {
				iResult = -1;
			}
			else {
				iResult = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			iResult = -1;
		}
		return iResult;
	}

	@Override
	public Notification getNotificationById(long id) {
		return notificationRepository.getById(id);
	}

	@Override
	public int deleteNotification(long id) {
		notificationRepository.deleteById(id);
		return 0;
	}
	
}
