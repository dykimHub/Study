package com.jpa.purchase.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {

	private Long id;

	private String name;

	private BigDecimal price;

	// 반환용
	// 관계 테이블 변수 없이 생성
	@Builder
	public ProductDto(Long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

}
