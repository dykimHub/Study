package com.jpa.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.User;
import com.jpa.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
//final, @nonnull 붙은 거만 생성자 생성
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	// 롬복이 autowired와 같은 기능 해줌
	private final UserService userService;

	@GetMapping("/users")
	@Operation(summary = "회원 목록")
	public ResponseEntity<?> getUserList() {
		List<User> users = userService.getUserList();

		if (users == null || users.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@PostMapping("/user")
	@Operation(summary = "회원 등록")
	public ResponseEntity<?> registUser(@RequestBody UserDto userDto) {

		try {
			Long result = userService.registUser(userDto);
			return new ResponseEntity<Long>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping("/user/update/{id}")
	@Operation(summary = "회원 수정")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {

		try {
			Long result = userService.updateUser(id, userDto);
			return new ResponseEntity<Long>(result, HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@DeleteMapping("/delete/{id}")
	@Operation(summary = "회원 삭제")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		
		boolean result = userService.deleteUser(id);

		if (!result)
			return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}
	
	@GetMapping("/user/{name}")
	@Operation(summary = "회원 찾기(이름)")
	public ResponseEntity<?> getUserByName(@PathVariable String name) {
		User user = userService.getUserByName(name);

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

}
