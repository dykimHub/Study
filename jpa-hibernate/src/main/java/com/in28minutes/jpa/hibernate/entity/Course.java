package com.in28minutes.jpa.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Course {
	
	@Id // primary key
	@GeneratedValue
	private Long id;
	
	private String name;
	
	public Course() {
		
	}
	
	public Course(String name) {
		this.name = name;
	}

	//id는 setter 없어도 됨
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
