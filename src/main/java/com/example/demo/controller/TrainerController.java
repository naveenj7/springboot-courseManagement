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
import com.example.demo.entity.Trainer;
import com.example.demo.repo.TrainerRepo;
import com.example.demo.services.TrainerService;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private TrainerRepo trainerRepo;
	
	@PostMapping("/register")
	public ResponseEntity<Object> addTrainerDetails(@RequestBody Trainer data) {
		return trainerService.addTrainer(data);	
	}
	
	@GetMapping("/all")
    public List<Trainer> getTrainees() {
        return trainerRepo.findAll();
    }
	
	@GetMapping("/student")
	public ResponseEntity<Object> trainerDetails(@RequestParam int trainerId) {
		return trainerService.trainerDetails(trainerId);	
	}
	
    @PutMapping("/update")
    public ResponseEntity<Object> updateTrainer( @RequestBody Trainer data) {
        return trainerService.updateTrainer(data);
    }
    
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteTrainer(@RequestParam int trainerId) {
		return trainerService.deleteTrainer(trainerId);	
	}
	

}
