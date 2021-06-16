package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name ="courses_information")
@NoArgsConstructor
@AllArgsConstructor
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int courseId;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "fees")
	private Double fees;
	
	@Column(name = "duration")
	private Double duration;

	@Transient
    @ManyToMany(targetEntity = Student.class, mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students;
	
	@Transient
    @ManyToMany(targetEntity = Trainer.class, mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Trainer> trainers;
}
