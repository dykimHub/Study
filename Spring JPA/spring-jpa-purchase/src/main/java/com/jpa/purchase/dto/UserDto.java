package com.jpa.purchase.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Long id;

	private String password;

	private String name;

	private LocalDate birthDate;

}
