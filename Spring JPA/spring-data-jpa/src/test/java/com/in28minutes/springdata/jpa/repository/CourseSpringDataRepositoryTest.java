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
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springdata.jpa.SpringDataJpaApplication;
import com.in28minutes.springdata.jpa.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@SpringBootTest(classes = SpringDataJpaApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Autowired
	CourseSpringDataRepository repository; // jparepository가 구현하고 있는 함수를 쓸 수 있음

	@Test
	public void findById_CoursePresent() {
		// 찾고자 하는 객체가 있으면 그 객체를 감싸서 Optional로 반환, 없으면 Optional.empty()를 반환.
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	@Transactional
	public void findById_firstLevelCacheDemo() {
		// first cache clear, second 영향X
		// 캐시 저장 용량이 커지면 그 또한 성능 저하의 원인이 될 수 있음
		// em.clear();

		Optional<Course> courseOptional1 = repository.findById(10001L);
		Course course1 = courseOptional1.get();
		logger.info("First Course Retrieved {}", course1);

		Optional<Course> courseOptional2 = repository.findById(10001L);
		Course course2 = courseOptional2.get();
		// 이 로그 기록이 first course retrieved 바로 밑에 나온다 원래는 course1을 찾는 쿼리문 밑에 나와야 하는데
		// @transaction 때문에 course entity가 persistence context돼서 똑같은거 찾을 때는 cache에서 찾음
		// transaction boundary안에서= 하나의 트랜잭션에서만 캐싱하는게 first level transaction
		logger.info("First Course Retrieved again {}", course2);

		assertEquals("JPA in 50 Steps", course1.getName());
		assertEquals("JPA in 50 Steps", course2.getName());
		
		// 추가 설명
		// 이 메서드는 @Transactional 때문에 하나의 트랜잭션으로 인식해서 properties에 second level해놨어도 first level에서 가져옴
		// 첫번째로 findbyid 할 때 first, second 둘다 저장되는데 first level에서 가져옴
		// @Transactional 없으면 하나의 트랜잭션으로 인식 못해서 그니까 findbyid 2번을 각각 다른 트랜잭션으로 인식해서 second level에서 가져오는데
		// *foreign key없는* 엔티티 조회하면 처음 조회할 때는 로그에 1 jdbc statements, 1 L2C misses 찍힘
		// (다대일, 다대다 테이블로 연결되면 그 연결된 테이블은 캐시에 저장 안됨 매번 다시 불러옴)
		// 근데 다시 조회하면 0 jdbc statements, 0 L2C misses라고 찍힘, 캐시에 없었는데 생겨서 쿼리문 안날리고 캐시에서 가져왔다는 뜻
		// 그니까 엔티티 상세 조회 메서드 안에서 1번 인덱스를 2번 불러서 2번째는 캐시에서 가져온다 그러면 1단곈데
		// 상세 조회 메서드 자체를 2번(1번 인덱스를 4번 부르는거지) 불렀을 때 캐시에 아직 저장돼서 3,4번째 부를 때도 캐시에서 불러온다 그러면 2단계
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002L);
		Optional<Course> courseOptional = repository.findById(10002L);
		// optional 객체가 비어있으면 null을 반환해라
		assertNull(courseOptional.orElse(null));

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
