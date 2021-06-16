package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Courses;
import com.example.demo.entity.Student;
import com.example.demo.repo.CoursesRepo;
import com.example.demo.repo.StudentRepo;


@Service
public class StudentService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CoursesRepo coursesRepo;
	

	public ResponseEntity<Object> addStudent(Student data) {
		
		if(!studentRepo.findByEmail(data.getEmail()).isPresent()) {
			Student student = studentRepo.save(data);
			if(!studentRepo.findById(student.getStudentId()).isPresent()){
				return ResponseEntity.unprocessableEntity().body("Failed to register");
			}
		}
		else {
		  return ResponseEntity.unprocessableEntity().body("student with this email is already Present");
		}
		return ResponseEntity.ok("Successfully registered");
	}


	public ResponseEntity<Object> updateStudent(Integer id, Student data) {

		if(studentRepo.findById(id).isPresent()) {
			
			Student student = studentRepo.findById(id).get();			
			List<Courses> courseList = data.getCourses();
			
			List<Courses> courses = new ArrayList<>();
			if(courseList.size()>0) {
				
        		for(Courses course : courseList) {
        			int courseId = course.getCourseId();
        			courses.add(coursesRepo.findById(courseId).get());     
        		}
        	}
			
			student.setCourses(courses);
			
			Student stud = studentRepo.save(student);
			
            if(studentRepo.findById(stud.getStudentId()).isPresent())
                return ResponseEntity.accepted().body("Student updated successfully");
            else return ResponseEntity.badRequest().body("Failed to update Student");
			
		}
		else return ResponseEntity.unprocessableEntity().body("Specified student not found");
	}



}
