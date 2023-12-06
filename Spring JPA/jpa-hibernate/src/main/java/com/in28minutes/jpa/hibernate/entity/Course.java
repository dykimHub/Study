package com.in28minutes.jpa.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// table 자동 생성 돼서 sql 파일에 createtable~ 할 필요 없음
@Entity
// @Table(name="Course") // 정확하게 테이블을 매핑하는 데 도움을 주는듯
public class Course {

	@Id // primary key
	@GeneratedValue
	private Long id;
	
	//@Column(name="fullname", nullable=false) // full name 행과 mapping, null가능인 행을 notnull로 변경
	private String name;

	protected Course() {

	}

	public Course(String name) {
		this.name = name;
	}

	// id는 setter 없어도 됨
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}

}
