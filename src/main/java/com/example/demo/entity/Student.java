package com.example.demo.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="student_information")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;

	@Length(min = 2, max = 60)
	@Column(name = "name")
	private String name;

	@Column(name = "dateOFBirth")
	private Date dateOFBirth;
	
	
	@Column(name = "mobileNo", unique = true)
	private long mobileNo;


	@Length(min = 6, max = 60)
	@Column(name = "email", unique = true)
	@Email
	private String email;
	
	@ManyToMany(targetEntity = Courses.class, cascade = CascadeType.ALL)
    private List<Courses> courses;
}
