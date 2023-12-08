package com.in28minutes.jpa.hibernate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Passport {

	@Id 
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;
	
	// student가 owning side of relationship 일 때, mappedBy에 student에 정의된 passport 변수 넣기
	// student entity만 passport_id 들어감, passport entity에는 student_id없음(중복 사라짐)
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport") 
	private Student student;

	protected Passport() {

	}

	public Passport(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}

}
