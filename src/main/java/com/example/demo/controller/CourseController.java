package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Courses;
import com.example.demo.repo.CoursesRepo;
import com.example.demo.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService service;
	
	@Autowired
	private CoursesRepo repo;
	
    @PostMapping("/create")
    public ResponseEntity<Object> createRole(@RequestBody Courses course) {
        return  service.addCourse(course);
    }
    
    @GetMapping("/all")
    public List<Courses> getCourses() {
        return repo.findAll();
    }
}
