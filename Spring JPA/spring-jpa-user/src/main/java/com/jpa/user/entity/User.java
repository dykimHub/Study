package com.jpa.user.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "USERS") // user로 하면 h2에 있는 user 명령어? 때문에 충돌해가지고 테이블이름 users로 설정
@Entity
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment key
	@Column(name = "id", nullable = false) // mysql 매핑 이름, not null
	private Long id;

	@JsonIgnore // 리턴할 때 json에서 숨김
	@Column(name = "password", nullable = false) // ,length = 10 -> 길이 제한
	private String password;

	// @NonNull
	@Column(name = "name", nullable = false) // ,unique = true -> 중복 방지
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	// 모든 필드가 있을 때만 builder로 생성 가능하게끔?? 생성자에 붙였음 클래스에 붙이는 것도 가능
	@Builder
	public User(Long id, String password, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
	}

}
