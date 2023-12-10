package com.in28minutes.jpa.inheritance.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// @Entity

// 상속된 거 한번에 보여주기 -> sql문 select, 해당하지 않는 열에 null값 들어가서 invalidate data 구분 못할 가능성 존재
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

// 상속된거 나눠서 보여주기 -> sql문 union, null은 없는데 겹치는 열 발생
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

// 상속한 엔티티, 상속 받은 엔티티 다 보여주기 -> null없고 중복 없는데(foreign key) left outer join 2번해서 복잡한 쿼리
// @Inheritance(strategy = InheritanceType.JOINED)

// inheritance(상속)아니고 join아니고 common(공통 속성) 정의라 @entity이거 없애야함 
// entity가 아니라서 employee는 별도로 생성 안됨 table per class, joined는 다 생성한다는 차이점
// 조회 쿼리 발생 안돼서 상속된 엔티티에서 각각 select문 사용해서 불러온다 위에 거는 employee entity 불러오면 상속된 거 불러올 수 있음
@MappedSuperclass

// 상속된 클래스 이름 보여주는 열 이름 default가 dtype 인데 그거 바꿔주는거
// @DiscriminatorColumn(name = "EmployeeType")
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected Employee() {

	}

	public Employee(String name) {
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

	@Override
	public String toString() {
		return String.format("Employee[%s]", name);
	}

}
