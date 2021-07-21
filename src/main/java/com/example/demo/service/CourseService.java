package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Course;


/**
 * Course Service Interface
 * @author SANG VO
 *
 */
public interface CourseService {
	List<Course> getAllCourses();
	
	Course saveCourse(Course course);
	
	Course updateCourse(Course course);
	
	int checkExistingCourse(long id);
	
	Course getCourseById(long id);

	int deleteCourse(long id);
	
}
