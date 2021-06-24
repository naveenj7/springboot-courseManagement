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

import com.example.demo.entity.Courses;
import com.example.demo.repo.CoursesRepo;
import com.example.demo.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CoursesRepo repo;
	
    @PostMapping("/create")
    public ResponseEntity<Object> createCourse(@RequestBody Courses course) {
        return  courseService.addCourse(course);
    }
    
    @GetMapping("/all")
    public List<Courses> getCourses() {
        return repo.findAll();
    }
    
    @GetMapping("/course")
    public ResponseEntity<Object> getCourse(@RequestParam  int courseId) {
        return courseService.getCourse(courseId);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCourse(@RequestParam  int courseId) {
        return  courseService.deleteCourse(courseId);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Object> updateCourse(@RequestBody Courses course) {
        return  courseService.updateCourse(course);
    }
    

   
}
