package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

/**
 * SANGVO 20210619 Student Management System
 * @author SANG VO
 */
@Controller
public class StudentController {
	private StudentService studentService; 
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	/**
	 * Default get mapping handle
	 * @param model
	 * @return
	 */
	@RequestMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	// -----------------------------------------------------------------------------------
	// POST URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Create a new student to list
	 * @param student: student object from "create_student" view
	 * @return
	 */
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
    }

	/**
	 * Update an existing student
	 * @param student: student object from "update_student" view
	 * @return
	 */
	@PostMapping("/students/{id}")
	public String updateStudent(@ModelAttribute("student") Student student) {
		studentService.updateStudent(student);
		return "redirect:/students";
    }
	
	// -----------------------------------------------------------------------------------
	// GET URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
    }

	/**
	 * When click "New Student" button on HP, then get existing student to update_student view
	 * @param id: Updated student id
	 * @param model: return object to view
	 * @return
	 */
	@GetMapping("/students/edit/{id}")
	public String updateStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "update_student";
    }
	
	/**
	 * Delete an existing student
	 * @param id: deleted student id
	 * @return
	 */
	@GetMapping("/students/delete/{id}")
	public String deleteStudentForm(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
    }

}
