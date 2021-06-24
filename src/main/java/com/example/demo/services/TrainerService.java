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
import com.example.demo.exception.ResourceNotFoundException;
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

	public ResponseEntity<Object> trainerDetails(int trainerId) {
		Trainer trainer = trainerRepo.findById(trainerId)
				.orElseThrow(() -> new ResourceNotFoundException("trainer Not Found!"));
		
		return ResponseHandler.generateResponse("Found the trainer", HttpStatus.OK, trainer);
	}

	public ResponseEntity<Object> updateTrainer(Trainer data) {
		Trainer trainer = trainerRepo.findById(data.getTrainerId())
				.orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found!"));
		
		trainer.setTrainerName(data.getTrainerName());

		trainer.getCourses().addAll(data.getCourses().stream().map(v -> {
			Courses vv = coursesRepo.findById(v.getCourseId()).get();
			vv.getTrainers().add(trainer);
			return vv;
		}).collect(Collectors.toList()));
		Trainer updatedTrainer = trainerRepo.save(trainer);

		if (updatedTrainer == null) {
			return ResponseHandler.generateResponse("Updation Failed", HttpStatus.MULTI_STATUS, null);
		} else {
			return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, trainer);
		}
	}

	public ResponseEntity<Object> deleteTrainer(int trainerId) {
		Trainer trainer = trainerRepo.findById(trainerId)
				.orElseThrow(() -> new ResourceNotFoundException("trainer Not Found!"));
		
		trainer.getCourses().forEach(course -> {
			course.getStudents().remove(trainer);
        });
		
		trainerRepo.deleteById(trainerId);
		return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, trainer);
	}

}
