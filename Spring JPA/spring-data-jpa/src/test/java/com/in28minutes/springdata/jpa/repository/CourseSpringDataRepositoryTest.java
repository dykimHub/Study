package com.in28minutes.springdata.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springdata.jpa.SpringDataJpaApplication;
import com.in28minutes.springdata.jpa.entity.Course;

import jakarta.persistence.EntityManager;

@SpringBootTest(classes = SpringDataJpaApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Autowired
	CourseSpringDataRepository repository; // jparepository가 구현하고 있는 함수를 쓸 수 있음

	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());

	}

	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());

	}

	@Test
	public void playAroundWithSpringDataRepository() {
		// jpa의 save 메서드는 insert와 update 둘다에 쓸 수 있다
		Course course = new Course("Microservices in 100 Steps");
		repository.save(course);

		course.setName("Microservices in 100 Steps - Updated");
		repository.save(course);

		logger.info("Courses -> {}", repository.findAll());
		logger.info("Courses -> {}", repository.count());
	}

	@Test
	public void sort() {
		Sort sort = Sort.by(Sort.Direction.DESC, "name"); // .and(sort~)해서 조건 추가가능
		logger.info("Sorted Courses -> {}", repository.findAll());
	}

	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);

		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {}", firstPage.getContent());

		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {}", secondPage.getContent());

	}
	
	@Test
	public void findUsingName() {
		logger.info("FindByName -> {}", repository.findByName("JPA in 50 Steps"));
	}
}
