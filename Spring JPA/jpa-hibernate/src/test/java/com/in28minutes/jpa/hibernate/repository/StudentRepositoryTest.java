package com.in28minutes.jpa.hibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.JpaHibernateApplication;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;

import jakarta.persistence.EntityManager;

//junittest 때문에 test에 application 또 만든듯?
@SpringBootTest(classes = JpaHibernateApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void someTest() {
		// studentrepository는 transactional annotation이 있어서 오류 없음
		// 이곳에서 실행한다면 메서드에 transactional annotation 필수; 엔티티 하나라도 오류나면 롤백해야돼서
		repository.someOperationToUnderStandPersistenceContext();
	}

	@Test
	@Transactional // lazy fetch일 때 필요
	public void retrieveStudentAndPassporDetails() {
		Student student = em.find(Student.class, 20001L);

		logger.info("student -> {}", student); // student entity 전체 검색

		// eager fetch에서는 passport entitiy 전체 검색
		// lazy fetch에서는 student entity의 passport entity만 검색
		logger.info("passport -> {}", student.getPassport());
	}
	
	// mappedby 이후 사용 가능한 메서드
	@Test
	@Transactional 
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);

		logger.info("passport -> {}", passport); // student entity 전체 검색
		logger.info("passport -> {}", passport.getStudent());
	}
	
	@Test
	@Transactional 
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}

}
