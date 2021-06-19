package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;


/**
 * Student Service Interface
 * @author SANG VO
 *
 */
public interface StudentService {
	List<Student> getAllStudents();
	
	int saveStudent(Student student);
	
	int updateStudent(Student student);
	
	int checkExistingStudent(long id);
	
	Student getStudentById(long id);

	int deleteStudent(long id);
	
}
