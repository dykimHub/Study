package com.jpa.purchase.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Data
// @Getter
// @Setter // 수정할 거 있으면 querydsl에 하는게 낫겠다
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 protected로 생성
// @AllArgsConstructor // 모든 필드를 파라미터를 받는 생성자; 모든 클래스를 외부로 노출하므로 위험
@Table(name = "USER")
@Cacheable
@Entity
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment key
	@Column(name = "id") // mysql 매핑 이름
	private Long id;

	@JsonIgnore // 리턴할 때 json에서 숨김
	@Column(name = "password", nullable = false) // ,length = 10 -> 길이 제한
	private String password;

	// @NonNull
	// unique 설정걸기 전에 테이블에 중복된 값 있으면 작동 안 함
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	
	// 오너테이블 설정
	// 서로 삭제했을 때 관계만 끊고 싶으면 cascade type 지정 안해도 됨
	@ManyToMany
	@JoinTable(
		name = "USER_PRODUCT", 
		joinColumns = @JoinColumn(name = "USER_ID"),
		inverseJoinColumns = @JoinColumn(name="PRODUCT_ID"))
	private List<Product> products = new ArrayList<>();

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
