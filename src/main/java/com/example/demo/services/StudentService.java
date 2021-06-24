package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseHandler;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.CoursesRepo;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CoursesRepo coursesRepo;

	public ResponseEntity<Object> addStudent(Student data) {

		if (!studentRepo.findByEmail(data.getEmail()).isPresent()) {

			Student newStudent = new Student();
			newStudent.setName(data.getName());
			newStudent.setEmail(data.getEmail());
			newStudent.setMobileNo(data.getMobileNo());
			newStudent.setDateOFBirth(data.getDateOFBirth());

			newStudent.getCourses().addAll(data.getCourses().stream().map(v -> {
				Courses vv = coursesRepo.findById(v.getCourseId()).get();
				vv.getStudents().add(newStudent);
				return vv;
			}).collect(Collectors.toList()));

			Student student = studentRepo.save(newStudent);
			if (!studentRepo.findById(student.getStudentId()).isPresent()) {
				return ResponseHandler.generateResponse("Failed to register", HttpStatus.MULTI_STATUS, null);
			} else {
				return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, student);
			}
		} else {
			return ResponseHandler.generateResponse("student with this email is already Present", HttpStatus.CONFLICT,
					null);
		}

	}

	public ResponseEntity<Object> updateStudent(Student data) {

		Student student = studentRepo.findById(data.getStudentId())
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found!"));
		
		student.setName(data.getName());
		student.setEmail(data.getEmail());
		student.setMobileNo(data.getMobileNo());
		student.setDateOFBirth(data.getDateOFBirth());
		
		student.getCourses().addAll(data.getCourses().stream().map(v -> {
			Courses vv = coursesRepo.findById(v.getCourseId()).get();
			vv.getStudents().add(student);
			return vv;
		}).collect(Collectors.toList()));
		Student stud = studentRepo.save(student);

		if (stud == null) {
			return ResponseHandler.generateResponse("Updation Failed", HttpStatus.MULTI_STATUS, null);
		} else {
			return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, student);
		}

	}

	public ResponseEntity<Object> deleteStudent(int studentId) {
		
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found!"));
		
		student.getCourses().forEach(course -> {
			course.getStudents().remove(student);
        });
		
		studentRepo.deleteById(studentId);
		return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, student);
	}

	public ResponseEntity<Object> studentDetails(int studentId) {
		
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found!"));
		
		return ResponseHandler.generateResponse("Found the Student", HttpStatus.OK, student);
	}

}
