package com.example.demo.controller.admin;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import com.example.demo.utility.FileUploadUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * SANGVO 20210619 Course Management System
 * @author SANG VO
 */
@Slf4j
@Controller
@RequestMapping("/admin/courses")
public class AD_CourseController {
	
	@Autowired
	private CourseService courseService; 
    private static final Logger logger = LoggerFactory.getLogger("CourseController.class");
	

	// -----------------------------------------------------------------------------------
	// GET URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Default get mapping handle
	 * @param model
	 * @return
	 */
	@RequestMapping()
	public String listcourses(Model model) {
        logger.info("/courses");
		model.addAttribute("courses", courseService.getAllCourses());
		return "admin/course/courses";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String createCourseForm(Model model) {
		Course Course = new Course();
		model.addAttribute("course", Course);
		return "admin/course/create_course";
    }

	/**
	 * When click "New Course" button on HP, then get existing Course to update_Course view
	 * @param id: Updated Course id
	 * @param model: return object to view
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String updateCourseForm(@PathVariable Long id, Model model) {
		model.addAttribute("course", courseService.getCourseById(id));
		return "admin/course/update_course";
    }
	
	/**
	 * Delete an existing Course
	 * @param id: deleted Course id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String deleteCourseForm(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return "redirect:/admin/courses";
    }

	// -----------------------------------------------------------------------------------
	// POST URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Create a new Course to list
	 * @param Course: Course object from "create_Course" view
	 * @return
	 * @throws IOException 
	 */
	@PostMapping()
	public String saveCourse(@RequestParam("avatar") MultipartFile multipartFile,
			@ModelAttribute("course") Course course) throws IOException {
		
		logger.info("saveCourse(@RequestParam(avatar)");

		// Get avatar file path and st
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		course.setPhoto(fileName);
		
		Course savedCourse = courseService.saveCourse(course);
		
		// Upload client image to server
		String uploadDir = "user-photos/" + savedCourse.getId();
		
		FileUploadUtility.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/admin/courses";
    }

	/**
	 * Update an existing Course
	 * @param Course: Course object from "update_Course" view
	 * @return
	 */
	@PostMapping("/{id}")
	public String updateCourse(@RequestParam("avatar") MultipartFile multipartFile,
			@ModelAttribute("course") Course course) throws IOException {

		logger.info("updateCourse(@RequestParam(avatar)");
		
		// Update New Information to DB
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		course.setPhoto(fileName);
		Course savedCourse = courseService.updateCourse(course);
		
		// Upload client image to server
		log.info(course.getPhoto());
		log.info("New image fileName: " + fileName);
		String uploadDir = "user-photos/" + savedCourse.getId();
		log.info("uploadDir: " + uploadDir + "; fileName: " + fileName);
		FileUploadUtility.saveFile(uploadDir, fileName, multipartFile);

		return "redirect:/admin/courses";
    }
	

}
