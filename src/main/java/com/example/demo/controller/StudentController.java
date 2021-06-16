package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Courses;
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
    public List<Student> getCourses() {
        return repo.findAll();
    }
	
    @PutMapping("/update")
    public ResponseEntity<Object> updateStudent(@RequestParam Integer studentId, @RequestBody Student data) {
        return service.updateStudent(studentId, data);
    }

}
