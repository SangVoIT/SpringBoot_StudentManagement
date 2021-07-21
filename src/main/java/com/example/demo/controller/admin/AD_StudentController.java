package com.example.demo.controller.admin;

import java.io.IOException;

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

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utility.FileUploadUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * SANGVO 20210619 Student Management System
 * @author SANG VO
 */
@Slf4j
@Controller
@RequestMapping("/admin/students")
public class AD_StudentController {
	
	@Autowired
	private StudentService studentService; 
	
	// -----------------------------------------------------------------------------------
	// GET URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Default get mapping handle
	 * @param model
	 * @return
	 */
	@RequestMapping()
	public String listStudents(Model model) {
        log.info("/students");
		model.addAttribute("students", studentService.getAllStudents());
		return "admin/student/students";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "admin/student/create_student";
    }

	/**
	 * When click "New Student" button on HP, then get existing student to update_student view
	 * @param id: Updated student id
	 * @param model: return object to view
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String updateStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "admin/student/update_student";
    }
	
	/**
	 * Delete an existing student
	 * @param id: deleted student id
	 * @return
	 * 
	 */
	@GetMapping("/delete/{id}")
	public String deleteStudentForm(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/admin/students";
    }

	// -----------------------------------------------------------------------------------
	// POST URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Create a new student to list
	 * @param student: student object from "create_student" view
	 * @return
	 * @throws IOException 
	 */
	@PostMapping()
	public String saveStudent(@RequestParam("avatar") MultipartFile multipartFile,
			@ModelAttribute("student") Student student) throws IOException {
		
		// Get avatar file path and st
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		student.setPhoto(fileName);
		
		Student savedStudent = studentService.saveStudent(student);
		
		// Upload client image to server
		String uploadDir = "user-photos/" + savedStudent.getId();
		
		FileUploadUtility.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/admin/students";
    }

	/**
	 * Update an existing student
	 * @param student: student object from "update_student" view
	 * @return
	 */
	@PostMapping("/{id}")
	public String updateStudent(@RequestParam("avatar") MultipartFile multipartFile,
			@ModelAttribute("student") Student student) throws IOException {
		
		// Update New Information to DB
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		student.setPhoto(fileName);
		Student savedStudent = studentService.updateStudent(student);
		
		// Upload client image to server
		System.out.println(student.getPhoto());
		System.out.println("New image fileName: " + fileName);
		String uploadDir = "user-photos/" + savedStudent.getId();
		System.out.println("uploadDir: " + uploadDir + "; fileName: " + fileName);
		FileUploadUtility.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/admin/students";
    }
	

}
