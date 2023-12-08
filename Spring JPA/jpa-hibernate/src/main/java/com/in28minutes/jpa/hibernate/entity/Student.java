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
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	// 학생 entity는 여권 entity와 일대일관계
	// eagerfetch 즉시로딩; 학생 엔티티 조회 쿼리, 여권 엔티티 조회 커리 같이 날림
	// lazyfetch 지연로딩; 학생 엔티티 조회 쿼리만 날림
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	protected Student() {

	}

	public Student(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}

}
