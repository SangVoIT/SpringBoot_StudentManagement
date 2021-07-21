package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

/**
 * Course Service Implement Class
 * @author SANG VO
 */
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course saveCourse(Course course) {
		try {
			// Check existing data
			if (course.getId() != null && checkExistingCourse(course.getId()) == 1) {
				return null;
			}
			
			//  Insert data
			return courseRepository.save(course);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Course updateCourse(Course course) {
		try {
			Course objOldCourse = courseRepository.getById(course.getId());
			
			// Setting new data to old data
			objOldCourse.setId(course.getId());
			objOldCourse.setTitle(course.getTitle());
			objOldCourse.setDescription(course.getDescription());
			objOldCourse.setDuration(course.getDuration());
			objOldCourse.setCost(course.getCost());
			
			return courseRepository.save(objOldCourse);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * Course check
	 */
	@Override
	public int checkExistingCourse(long id) {
		// TODO Auto-generated method stub
		int iResult = -1;
		Course Course;
		try {
			Course = courseRepository.findById(id).get();
			if (Course.getId() < 1) {
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
	public Course getCourseById(long id) {
		return courseRepository.getById(id);
	}

	@Override
	public int deleteCourse(long id) {
		courseRepository.deleteById(id);
		return 0;
	}
	
}
