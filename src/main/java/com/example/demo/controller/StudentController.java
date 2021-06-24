package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@Autowired
	private StudentRepo repo;
	
	@PostMapping("/register")
	public ResponseEntity<Object> addStudentDetails(@RequestBody Student data) {
		return service.addStudent(data);	
	}
	
	@GetMapping("/all")
    public List<Student> getStudents() {
        return repo.findAll();
    }
	
	@GetMapping("/student")
	public ResponseEntity<Object> studentDetails(@RequestParam int studentId) {
		return service.studentDetails(studentId);	
	}
	
    @PutMapping("/update")
    public ResponseEntity<Object> updateStudent( @RequestBody Student data) {
        return service.updateStudent(data);
    }
    
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteStudent(@RequestParam int studentId) {
		return service.deleteStudent(studentId);	
	}
	

}
