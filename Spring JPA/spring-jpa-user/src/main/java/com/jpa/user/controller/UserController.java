package com.jpa.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.User;
import com.jpa.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
//final, @nonnull 붙은 거만 생성자 생성
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	// 롬복이 autowired와 같은 기능 해줌
	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<?> getUserList() {
		List<User> users = userService.getUserList();

		if (users == null || users.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@PostMapping("/user")
	public ResponseEntity<?> registUser(@RequestBody UserDto userDto) {

		try {
			int result = userService.registUser(userDto);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		User user = userService.getUserById(id);

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	
	// 쿼리 디에스엘로 해보자
//	@PostMapping("/updateduser/{id}")
//	public ResponseEntity<?> registUser(@RequestBody UserDto userDto, @PathVariable int id) {
//		
//		try {
//			int result = userService.updateUser(id, userDto);
//			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
//
//		} catch (Exception e) {
//			String errorMessage = e.getMessage();
//			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//
//		}
//
//	}

}
