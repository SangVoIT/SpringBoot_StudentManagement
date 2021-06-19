package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@SpringBootApplication
public class StudentManagementApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		// When startup, some new student will be created
//		Student student1 = new Student("Huong", "Vo", "huongvo@gmail.com");
//		studentRepository.save(student1);
//
//		Student student2 = new Student("Sang", "Vo", "sangvo.it[gmail.com");
//		studentRepository.save(student2);
//
//		Student student3 = new Student("Thuy", "Tran", "thuytran96.cntt@gmial.com");
//		studentRepository.save(student3);
	}

}
