package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Trainer;

public interface TrainerRepo extends JpaRepository<Trainer, Integer> {

}
