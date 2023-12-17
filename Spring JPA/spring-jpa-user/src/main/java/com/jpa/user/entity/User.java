package com.jpa.user.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @getter, @setter, @RequiredArgsConstructor , toString() 자동생성
// 근데 @setter를 무분별하게 쓰게 돼서 무의미한 객체가 생성될 가능성 존재 
// @Getter로 하고 setter는 원하는 필드만 직접 만드는 방법이 있음
@Data 
// @Getter
// @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 protected로 생성
// @AllArgsConstructor // 모든 필드를 파라미터를 받는 생성자; 모든 클래스를 외부로 노출하므로 위험
@Entity
@Cacheable
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment key
	@Column(nullable = false)
	private int id;

	@JsonIgnore // 리턴할 때 json에서 숨김
	@Column(nullable = false)
	private String password;

	// @NonNull
	@Column(nullable = false)//, unique = true
	private String name;

	@Column(nullable = false)
	private LocalDate birthDate;
	
	// 모든 필드가 있을 때만 builder로 생성 가능하게끔?? 생성자에 붙였음 클래스에 붙이는 것도 가능
	@Builder 
	public User(int id, String password, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
	}

}
