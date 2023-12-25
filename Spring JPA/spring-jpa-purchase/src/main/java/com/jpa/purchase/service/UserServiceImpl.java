package com.jpa.purchase.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.repository.product.ProductRepository;
import com.jpa.purchase.repository.user.UserRepository;
import com.jpa.purchase.repository.user.UserRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
// final, @nonnull 붙은 거만 생성자 생성
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public List<UserDto> getUserList() throws NotFoundException {

		List<User> users = userRepository.findAll();

		if (users.isEmpty())
			throw new NotFoundException();

		List<UserDto> userDtos = users.stream()
				.map(u -> UserDto.builder()
						.id(u.getId())
						.name(u.getName())
						.birthDate(u.getBirthDate())
						.build())
				.collect(Collectors.toList());

		return userDtos;
	}

	public Long registUser(UserDto userDto) {
		// 보안 때문에 POST 메서드는 프론트단에서 UserDto로 객체를 받고 -> 서비스단으로 UserDto를 보내고 ->
		// 서비스단에서 Dto에 있는 값을 Entity로 바꿔서 -> Repository에 저장
		// Dto는 데이터베이스와는 관련이 없고 데이터 전송시에만 이용
		
		if (getUserByName(userDto.getName()) != null)
			throw new DataIntegrityViolationException("Duplicate Name");

		User user = User.builder()
				.password(userDto.getPassword())
				.name(userDto.getName())
				.birthDate(userDto.getBirthDate())
				.build();

		// jpa repository 상속받아서 쓰는 save는 알아서 transactional 하게 해줌
		User savedUser = userRepository.save(user);

		return savedUser.getId();

	}

	public Long updateUser(Long id, UserDto userDto) throws NotFoundException {

		if (userRepository.findById(id).isEmpty())
			throw new NotFoundException();
		
		User user = User.builder()
				.password(userDto.getPassword())
				.name(userDto.getName())
				.birthDate(userDto.getBirthDate())
				.build();

		return userRepository.updateUser(id, user);

	}

	@Override
	public void deleteUser(Long id) throws NotFoundException {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());

		userRepository.deleteById(id);

	}

	@Override
	public UserDto getUserByName(String name) {

		User user = userRepository.findByName(name);

		if (user != null) {
			UserDto userDto = UserDto.builder()
					.id(user.getId())
					.name(user.getName())
					.birthDate(user.getBirthDate())
					.build();

			return userDto;

		}

		return null;
	}

	@Override
	public Long buyProduct(Long userId, Long productId) throws NotFoundException {

		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException());
		Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException());

		// user테이블에만 추가해도 product 테이블에는 자동 매핑
		user.getProducts().add(product);
		User buyProduct = userRepository.save(user);

		// 사용자 아이디 반환
		return buyProduct.getId();

	}

	@Override
	public List<ProductDto> getProductByUser(Long id) throws NotFoundException {
		
		// 회원이 없는 경우
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());

		List<ProductDto> productDtos = user.getProducts().stream() // mapping 하려고 펼치는 거
				.map(p -> ProductDto.builder()
						.id(p.getId())
						.name(p.getName())
						.price(p.getPrice())
						.build()) // 매핑 하려는 함수
				.collect(Collectors.toList()); // 리스트로 변환
		
		if(productDtos.isEmpty())
			throw new NotFoundException();

		return productDtos;

	}

}
