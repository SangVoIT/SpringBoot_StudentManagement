package com.example.demo.controller.admin;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Notification;
import com.example.demo.service.NotificationService;
import com.example.demo.utility.FileUploadUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * SANGVO 20210619 notification Management System
 * @author SANG VO
 */
@Slf4j
@Controller
@RequestMapping("/notifies")
public class AD_NotificationController {
	private NotificationService notificationService; 
	
	public AD_NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}

	// -----------------------------------------------------------------------------------
	// GET URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Default get mapping handle
	 * @param model
	 * @return
	 */
	@RequestMapping()
	public String listnotifies(Model model) {
        log.info("/notifies");
		model.addAttribute("notifies", notificationService.getAllNotifications());
		return "notifies";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String createnotificationForm(Model model) {
		Notification notification = new Notification();
		model.addAttribute("notification", notification);
		return "create_notification";
    }

	/**
	 * When click "New notification" button on HP, then get existing notification to update_notification view
	 * @param id: Updated notification id
	 * @param model: return object to view
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String updatenotificationForm(@PathVariable Long id, Model model) {
		model.addAttribute("notification", notificationService.getNotificationById(id));
		return "update_notification";
    }
	
	/**
	 * Delete an existing notification
	 * @param id: deleted notification id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String deletenotificationForm(@PathVariable Long id) {
		notificationService.deleteNotification(id);
		return "redirect:/notifies";
    }

	// -----------------------------------------------------------------------------------
	// POST URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Create a new notification to list
	 * @param notification: notification object from "create_notification" view
	 * @return
	 * @throws IOException 
	 */
	@PostMapping()
	public String savenotification(@RequestParam("avatar") MultipartFile multipartFile,
			@ModelAttribute("notification") Notification notification) throws IOException {
		
		// Get avatar file path and st
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		notification.setPhoto(fileName);
		
		Notification savednotification = notificationService.saveNotification(notification);
		
		// Upload client image to server
		String uploadDir = "user-photos/" + savednotification.getId();
		
		FileUploadUtility.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/notifies";
    }

	/**
	 * Update an existing notification
	 * @param notification: notification object from "update_notification" view
	 * @return
	 */
	@PostMapping("/{id}")
	public String updatenotification(@RequestParam("avatar") MultipartFile multipartFile,
			@ModelAttribute("notification") Notification notification) throws IOException {
		
		// Update New Information to DB
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		notification.setPhoto(fileName);
		Notification savednotification = notificationService.updateNotification(notification);
		
		// Upload client image to server
		System.out.println(notification.getPhoto());
		System.out.println("New image fileName: " + fileName);
		String uploadDir = "user-photos/" + savednotification.getId();
		System.out.println("uploadDir: " + uploadDir + "; fileName: " + fileName);
		FileUploadUtility.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/notifies";
    }
	

}
