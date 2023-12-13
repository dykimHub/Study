package com.in28minutes.springdata.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springdata.jpa.SpringDataJpaApplication;
import com.in28minutes.springdata.jpa.entity.Address;
import com.in28minutes.springdata.jpa.entity.Passport;
import com.in28minutes.springdata.jpa.entity.Student;

import jakarta.persistence.EntityManager;

//junittest 때문에 test에 application 또 만든듯?
@SpringBootTest(classes = SpringDataJpaApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void someTest() {
		repository.someOperationToUnderStandPersistenceContext();
	}

	@Test
	@Transactional
	public void retrieveStudentAndPassporDetails() {
		Student student = em.find(Student.class, 20001L);

		logger.info("student -> {}", student); 
		logger.info("passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional 
	public void setAddressDetails() {
		Student student = em.find(Student.class, 20001L);
		// embedded한 행을 직접 변경함
		student.setAddress(new Address("No 101","Some Street","Hyderabad"));
		em.flush();

		logger.info("student -> {}", student); 
		logger.info("passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional 
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);

		logger.info("passport -> {}", passport); 
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
