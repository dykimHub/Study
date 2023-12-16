package com.jpa.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.User;
import com.jpa.user.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
// final, @nonnull 붙은 거만 생성자 생성
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	public List<User> getUserList() {
		return userRepository.findAll();
	}

	public int registUser(UserDto userDto) {
		// 전체 필드를 가지고 있는 생성자에만 builder를 붙였기 때문에
		// 필드 하나라도 누락되면 데이터 전송이 안됨
		// id는 generatedvalue라 ㄱㅊㄱㅊ

		User user = User.builder()
				.password(userDto.getPassword())
				.name(userDto.getName())
				.birthDate(userDto.getBirthDate())
				.build();
		
		// jpa repository 상속받아서 쓰는 save는 알아서 transactional 하게 해줌
		User savedUser = userRepository.save(user);

		return savedUser.getId();

	}

}
