package com.jpa.purchase.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.jpa.purchase.SpringJpaPurchaseApplication;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.repository.user.UserRepository;

// script null에러 조심.. resources에 schema.sql에 따로 만들거나 data.sql에 초기데이터랑 같이 만들면 됨
// application-{profile name} 이거라서 application 분리하고 이름 인식할 때 사용
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringJpaPurchaseApplication.class)
public class UserRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(2)
	public void findById() {
		User user = userRepository.findByName("kimdayun");
		
		Optional<User> userOptional = userRepository.findById(1L);
		assertTrue(userOptional.isPresent());
		
		// 캐시에서 가져옴
		User user2 = userRepository.findByName("kimdayun");
	}

	@Test
	@Order(1)
	// @BeforeAll
	public void saveUser() {
		User user = User.builder()
				.password("password")
				.name("kanginlee")
				.birthDate(LocalDate.parse("2001-02-19"))
				.build();

		userRepository.save(user);

		// 영속성 컨텍스트 때문에 변경 감지 가능
		
		user.setName("heunngminson");
		userRepository.save(user);

		logger.info("Users -> {}", userRepository.findAll());
		logger.info("Count Users -> {}", userRepository.count());
	}

}