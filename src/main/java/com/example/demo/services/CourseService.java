package com.example.demo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Courses;
import com.example.demo.repo.CoursesRepo;

@Service
public class CourseService {
	
	@Autowired
	private CoursesRepo repo;

	@Transactional
	public ResponseEntity<Object> addCourse(Courses course) {
		if(!repo.findByCourseName(course.getCourseName()).isPresent()) {
			Courses courses = repo.save(course);
			if(!repo.findById(courses.getCourseId()).isPresent()){
				return ResponseEntity.unprocessableEntity().body("Course Creation Failed");
			}
		}
		else {
		  return ResponseEntity.unprocessableEntity().body("course with this name is already Present");
		}
		return ResponseEntity.ok("Successfully created Course");
	}

}
