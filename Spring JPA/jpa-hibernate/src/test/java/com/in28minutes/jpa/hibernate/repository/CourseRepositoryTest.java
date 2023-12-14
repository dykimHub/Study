package com.in28minutes.jpa.hibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.JpaHibernateApplication;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;

import jakarta.persistence.EntityManager;

// junittest == 단위테스트
// junittest 때문에 test에 application 또 만든듯?
// 단위테스트는 h2-console(in-memory database) 사용하면 좋다
// 단위테스트에서만 사용하는 데이터는 data.sql이라는 이름으로 src/test/java/resources에 옮기면 됨
// assertequals 등은 단위테스트에서만 사용
@SpringBootTest(classes = JpaHibernateApplication.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Autowired
	CourseRepository repository;

	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		// Course course = repository.findById(10002L); // dirtiescontext때문에 10002없다고 나옴
		// assertEquals("JPA in 100 Steps", course.getName()); // failure trace에 자세한 오류
		// 이유 있음
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	// dirties context는 애플리케이션 컨텍스트 공유를 허용하지 않는 어노테이션
	// 나만의 실험을 하는 넉낌. 내 실험용 메서드임 그니까 데이터는 원래 상태로 보존되어야 하는 것
	// 만약에 다른 개발자가 select all 해보면 update 전 데이터로 출력-> 데이터 영속성? 유지
	// 트랜잭션이랑 다른 점은 트랜잭션은 오류가 나면 롤백이고 안나면 commit하는데 얘는 오류 안나도 롤백하는 느낌
	@DirtiesContext
	public void save_basic() {
		// get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

		// update details
		course.setName("JPA in 50 Steps - Updated");

		repository.save(course);

		// check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

	// one to many relationship
	@Test
	@Transactional // 메서드 끝 줄까지 성공이 아니면 데이터 변환X -> 데이터 영속성 유지
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		logger.info("{}", course.getReviews());
	}

	// many to one relationship
	@Test
	// 통상적으로 사용하는 격리레벨은 read_committed임 application context에 추가 설명 적어놨당
	// @Transactional(isolation = Isolation.READ_COMMITTED)
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}

}
