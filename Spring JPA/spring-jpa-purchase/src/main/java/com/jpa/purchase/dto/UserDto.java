package com.jpa.purchase.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @getter, @setter, @RequiredArgsConstructor , toString() 자동생성
// 근데 @setter를 무분별하게 쓰게 돼서 무의미한 객체가 생성될 가능성 존재 
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AllArgsConstructor // 모든 필드를 파라미터를 받는 생성자; 모든 클래스를 외부로 노출하므로 위험
public class UserDto {

	private Long id;

	// 반환할 때는 비밀번호 보이면 안돼서 뺌
	// 려고 했는데 등록/수정할 때 필요해서 다시 추가함
	private String password;

	private String name;

	private LocalDate birthDate;

	// 반환용 메서드 
	// auto increment가 아닌 해당 아이디의 객체 반환해야해서 id 필드 필요
	// 비밀번호 제외하고 반환
	// 관계형 테이블도 제외하고 반환
	@Builder
	public UserDto(Long id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
}
