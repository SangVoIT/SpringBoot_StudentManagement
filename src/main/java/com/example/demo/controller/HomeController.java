package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CourseService;
import com.example.demo.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/home")
public class HomeController {
	

	@Value("${userImageFolder.path}")
	private String userImageFolderPath;
	@Value("${adminImageFolder.path}")
	private String adminImageFolderPath;
	@Value("${user.dir}")
	private String userPath;

	@Value("${adminImageFolder_2.path}")
	private String adminImageFolder_2path;
	
	@Value("${demoload}")
	private String demoload;
	
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private NotificationService notificationService;
	/***
	 * Home　page
	 * 
	 * @param signupForm サインアップフォームデータ
	 * @param model モデル（ユーザーリスト）
	 * @return index
	 */
	@GetMapping
	public String index(Model model) {
			// @ModelAttribute("signup") SignupForm signupForm, Model model) {
		System.out.println("HomeController: @GetMapping(/)");
		log.debug("HomeController: @GetMapping(/)");

		log.info("userPath: " + userPath);
		
		log.info("userImageFolderPath: " + userImageFolderPath);
		log.info("adminImageFolderPath: " + adminImageFolderPath);
		System.out.println("userPath: " + userPath 
				+ "; userImageFolderPath: " + userImageFolderPath
				+ "; adminImageFolderPath: " + adminImageFolderPath);
		
		log.info("adminImageFolder_2path: " + adminImageFolder_2path);
		log.info("demoload: " + demoload);
		
		// Get all Information Courses
		model.addAttribute("courseList", courseService.getAllCourses());
		
		// Get all notifications
		model.addAttribute("notifyList", notificationService.getAllNotifications());
		return "home/index";
	}
	
}
