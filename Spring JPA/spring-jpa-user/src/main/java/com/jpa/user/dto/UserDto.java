package com.jpa.user.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data // dto는 pathvariable 때문에 setter 필요해서..
@NoArgsConstructor
public class UserDto {
	// 보안 때문에 프론트단에서 UserDto로 객체를 받고 -> 서비스단으로 UserDto를 보내고 ->
	// 서비스단에서 Dto에 있는 값을 Entity로 바꿔서 -> Repository에 저장
	// Dto는 데이터베이스와는 관련이 없고 데이터 전송시에만 이용

	private int id;

	private String password;

	private String name;

	private LocalDate birthDate;

}
