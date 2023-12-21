package com.in28minutes.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	// 학생 entity는 여권 entity와 일대일관계
	// 일대일은 eager fetch default
	// eagerfetch 즉시로딩; 학생 엔티티 조회 쿼리, 여권 엔티티 조회 커리 같이 날림
	// lazyfetch 지연로딩; 학생 엔티티 조회 쿼리만 날림
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	// many to many는 lazy fetch가 default, eager fetch 변환 가능하나 권장X 이유? student, course 전부 retrieve 해서
	@ManyToMany
	// owning side에 쓸 수 있음
	// foreign key (course_id) 형성
	// 다대다 관계에서는 어떤 테이블에 ~id열을 추가헤도 값이 여러개임. 그래서 따로 entity만들어 줘야함 
	// joincolumns는 현재 엔티티의 외래키를 지정, inversejoincolumns는 연결된 엔티티의 외래키 지정
	@JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	private List<Course> courses = new ArrayList<>();

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

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourses(Course course) {
		this.courses.add(course);
	}

	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}

}
