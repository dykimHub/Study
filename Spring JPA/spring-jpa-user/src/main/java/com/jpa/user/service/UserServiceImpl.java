package com.jpa.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.User;
import com.jpa.user.repository.user.UserRepository;
import com.jpa.user.repository.user.UserRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
// final, @nonnull 붙은 거만 생성자 생성
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public List<User> getUserList() {
		return userRepository.findAll();
	}

	public Long registUser(UserDto userDto) {
		// 보안 때문에 POST 메서드는 프론트단에서 UserDto로 객체를 받고 -> 서비스단으로 UserDto를 보내고 ->
		// 서비스단에서 Dto에 있는 값을 Entity로 바꿔서 -> Repository에 저장
		// Dto는 데이터베이스와는 관련이 없고 데이터 전송시에만 이용

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
	
	public Long updateUser(Long id, UserDto userDto) {
		
		// 반환값이 Optional<User> 이거라서 user 있으면 반환하고, 없으면 null 반환하게끔
		User user = userRepository.findById(id).orElse(null);
		
		if(user == null)
			return 0L;
		
		User updatedUser = User.builder()
				.password(userDto.getPassword())
				.name(userDto.getName())
				.birthDate(userDto.getBirthDate())
				.build();

		return userRepository.updateUser(id, updatedUser);
	}

	@Override
	public boolean deleteUser(Long id) {
		
		User user = userRepository.findById(id).orElse(null);
		
		if (user == null)
			return false;

		userRepository.deleteById(id);

		return true;
	}

	@Override
	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}
}
