package com.jpa.purchase.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//기본 생성자 없으면 default 에러 나는데 있으면 필드가 비어있어도 db에 들어갈 상황을 대비하여 접근제어자 protected로
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) // 클래스용 캐시 어노테이션
@Entity
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // h2 db 때문에 strategy 설정해야함
	@Column(name = "id") // mysql 매핑 이름
	private Long id; // Long은 null 값을 나타낼 수 있음

	@Column(name = "password", nullable = false) // ,length = 10 -> 길이 제한
	private String password;

	// unique 설정걸기 전에 테이블에 중복된 값 있으면 작동 안 함
	// 그리고 unique 설정하니까 일단 들어오고(id 증가) 중복이면 빼는거라 서비스 단에서 처리하는게 나을듯
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	// 전송용 메서드
	// @Builder를 모든 필드를 가지는 생성자 메서드 위에만 붙이면 모든 필드를 설정해야만 새로운 객체 형성 가능
	// @RequireArgsConstructor 보다 필드 빼먹을 위험이 줄어든다
	// 클래스 위에 붙이면 선택적으로 필드 설정 가능
	// id는 자동 증가라 생성자에서 뺐다
	@Builder
	public User(String password, String name, LocalDate birthDate) {
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
	}

	// 오너테이블 설정
	// 서로 삭제했을 때 관계만 끊고 싶으면 cascade type 지정 안해도 됨
	@ManyToMany
	@JoinTable(name = "USER_PRODUCT", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private List<Product> products = new ArrayList<>();

}
