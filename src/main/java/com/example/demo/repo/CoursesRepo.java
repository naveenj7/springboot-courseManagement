package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Courses;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {

	Optional<Courses> findByCourseName(String courseName);


}
