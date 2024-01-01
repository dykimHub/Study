package com.jpa.purchase.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jpa.purchase.SpringJpaPurchaseApplication;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.repository.product.ProductRepository;
import com.jpa.purchase.repository.user.UserRepository;

import jakarta.transaction.Transactional;

// script null에러 조심.. resources에 schema.sql에 따로 만들거나 data.sql에 초기데이터랑 같이 만들면 됨
// application-{profile name} 이거라서 application 분리하고 이름 인식할 때 사용
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringJpaPurchaseApplication.class)
public class UserRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	@Order(1)
	public void registUser() {
		
		logger.info("Before User Save -> {}", userRepository.count());

		User user = User.builder()
				.password("kang1234")
				.name("kang")
				.birthDate(LocalDate.parse("2001-02-19"))
				.build();

		userRepository.save(user);

		// logger.info("Users -> {}", userRepository.findAll()); // 관계형 테이블 스택오버플로우
		logger.info("After User Save -> {}", userRepository.count());

	}

	@Test
	public void updateUser() {
		// update는 jpa repository에서 transactional 관리
		
		logger.info("Before User Update -> {}", userRepository.findById(2L).get().getName());

		User user = User.builder()
				.name("lee")
				.password("lee1234")
				.birthDate(LocalDate.parse("1992-08-10"))
				.build();

		userRepository.updateUser(2L, user);

		logger.info("After User Update -> {}", userRepository.findById(2L).get().getName());
	}

	@Test
	public void deleteUser() {

		Optional<User> user = userRepository.findById(1L);

		userRepository.deleteById(user.get().getId());

		assertFalse(userRepository.findById(1L).isPresent());

	}

	@Test
	public void getUserByName() {

		User user = userRepository.findByName("cho");
		
		assertTrue(user != null);
		
		// @transactional 안 넣고 세컨드 캐시 사용 확인
		logger.info("findByName(cho) -> {}", userRepository.findByName("cho"));

	}

	@Test
	@Transactional // user에 add 수행하고 출력해야돼서 필수
	public void buyProduct() {

		User user = userRepository.findById(2L).orElse(null);
		Product product = productRepository.findById(2L).orElse(null);
		
		assertTrue(user.getProducts().size() == 0);

		user.getProducts().add(product);
		
		assertTrue(user.getProducts().size() > 0);
		
		logger.info("Count Products of User -> {}", user.getProducts().size()); 
		
	}

}
