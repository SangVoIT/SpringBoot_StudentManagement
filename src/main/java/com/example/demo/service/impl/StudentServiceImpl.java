package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

/**
 * Student Service Implement Class
 * @author SANG VO
 */
@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		try {
			// Check existing data
			if (student.getId() != null && checkExistingStudent(student.getId()) == 1) {
				return null;
			}
			
			//  Insert data
			return studentRepository.save(student);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Student updateStudent(Student student) {
		try {
			Student objOldStudent = studentRepository.getById(student.getId());
			
			// Setting new data to old data
			objOldStudent.setFirstName(student.getFirstName());
			objOldStudent.setLastName(student.getLastName());
			objOldStudent.setEmail(student.getEmail());
			objOldStudent.setPhoto(student.getPhoto());
			
			return studentRepository.save(objOldStudent);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * Student check
	 */
	@Override
	public int checkExistingStudent(long id) {
		// TODO Auto-generated method stub
		int iResult = -1;
		Student student;
		try {
			student = studentRepository.findById(id).get();
			if (student.getId() < 1) {
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
	public Student getStudentById(long id) {
		return studentRepository.getById(id);
	}

	@Override
	public int deleteStudent(long id) {
		studentRepository.deleteById(id);
		return 0;
	}
	
}
