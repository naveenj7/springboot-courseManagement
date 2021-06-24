package com.example.demo.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseHandler;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Student;
import com.example.demo.entity.Trainer;
import com.example.demo.repo.CoursesRepo;
import com.example.demo.repo.TrainerRepo;

@Service
public class TrainerService {

	@Autowired
	private TrainerRepo trainerRepo;

	@Autowired
	private CoursesRepo coursesRepo;

	public ResponseEntity<Object> addTrainer(Trainer data) {

		Trainer newTraine = new Trainer();
		newTraine.setTrainerName(data.getTrainerName());
		newTraine.getCourses().addAll(data.getCourses().stream().map(v -> {
			Courses vv = coursesRepo.findById(v.getCourseId()).get();
			vv.getTrainers().add(newTraine);
			return vv;
		}).collect(Collectors.toList()));

		Trainer traine = trainerRepo.save(newTraine);
		if (!trainerRepo.findById(traine.getTrainerId()).isPresent()) {
			return ResponseHandler.generateResponse("Failed to register", HttpStatus.MULTI_STATUS, null);
		} else {
			return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, traine);
		}

	}

}
