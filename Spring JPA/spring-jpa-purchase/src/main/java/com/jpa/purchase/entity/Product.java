package com.jpa.purchase.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) 
@Table(name = "PRODUCT")
@Cacheable
@Entity
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	// 등록용
	@Builder
	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	@ManyToMany(mappedBy = "products")
	// jackson library, 출력할 때 객체에서 숨겨줌 
	// 순환 참조를 쉽게 방지하는 방식이긴 하나 지양해야해서 dto로 반환할 필드만 따로 생성자 만드는 방식 추천
	// @JsonIgnore
	private List<User> users = new ArrayList<>();
	
}
