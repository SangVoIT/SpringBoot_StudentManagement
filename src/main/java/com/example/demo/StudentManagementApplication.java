package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.FilesStorageService;

/***
 * Spring Boot Main Entry
 * @author SANGVO
 *
 */
@SpringBootApplication
public class StudentManagementApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository; 

	@Autowired
	private FilesStorageService storageService; 
	
	@Override
	public void run(String... args) throws Exception {

		System.out.println("0: StudentManagementApplication; run CommandLineRunner");
//		When startup, some new student will be created
		
		// 1_ Create new object for list Data, testing
//		Student student1 = new Student("Huong", "Vo", "huongvo@gmail.com");
//		studentRepository.save(student1);
//
//		Student student2 = new Student("Sang", "Vo", "sangvo.it[gmail.com");
//		studentRepository.save(student2);
//
//		Student student3 = new Student("Thuy", "Tran", "thuytran96.cntt@gmial.com");
//		studentRepository.save(student3);
		

		// 2_ Update data by Image, testing
//		Clear all old files and create new folder
//	    storageService.deleteAll();
//	    storageService.init();
		
		
		// 3_Base64Image testing
//		RestfulClient restfulClient = new RestfulClient();
//		restfulClient.postEntity();
		
	}

}
