package com.jpa.user.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data // dto는 pathvariable 때문에 setter 필요해서..
@NoArgsConstructor
public class UserDto {

	private Long id;

	private String password;

	private String name;

	private LocalDate birthDate;

}
