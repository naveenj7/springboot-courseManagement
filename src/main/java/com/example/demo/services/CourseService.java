package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseHandler;
import com.example.demo.entity.Courses;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.CoursesRepo;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CoursesRepo courseRepo;

	public ResponseEntity<Object> addCourse(Courses course) {
		if (!courseRepo.findByCourseName(course.getCourseName()).isPresent()) {
			Courses courses = courseRepo.save(course);
			if (!courseRepo.findById(courses.getCourseId()).isPresent()) {
				return ResponseHandler.generateResponse("Course Creation Failed", HttpStatus.MULTI_STATUS, null);
			}
			return ResponseHandler.generateResponse("Successfully created Course!", HttpStatus.CREATED, courses);
		} else {
			return ResponseHandler.generateResponse("course with this name is already Present", HttpStatus.CONFLICT,
					null);
		}
	}

	public ResponseEntity<Object> deleteCourse(int courseId) {
		Courses course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found!"));
		
        course.getStudents().forEach(student -> {
        	student.getCourses().remove(course);
        });
        
        course.getTrainers().forEach(trainee -> {
        	trainee.getCourses().remove(course);
        });

		courseRepo.deleteById(courseId);
		return ResponseHandler.generateResponse("Successfully Deleted data!", HttpStatus.OK, course);
	}

	public ResponseEntity<Object> updateCourse(Courses data) {
		
		Courses course = courseRepo.findById(data.getCourseId())
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found!"));
		
		course.setCourseName(data.getCourseName());
		course.setFees(data.getFees());
		course.setDuration(data.getDuration());
		
		Courses courses = courseRepo.save(course);
		
		return ResponseHandler.generateResponse("Successfully updated Course!", HttpStatus.CREATED, courses);
		
	}

	public ResponseEntity<Object> getCourse(int courseId) {
		Courses course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found!"));
		
		return ResponseHandler.generateResponse("Found the data!", HttpStatus.OK, course);
	}

}
