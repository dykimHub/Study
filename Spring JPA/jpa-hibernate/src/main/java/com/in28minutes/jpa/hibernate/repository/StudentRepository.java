package com.in28minutes.jpa.hibernate.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {

		if (student.getId() == null) {
			em.persist(student); // insert
		} else {
			em.merge(student); // update
		}

		return student;
	}

	public void deleteById(Long id) {
		Student student1 = findById(id);
		em.remove(student1);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport); // passport entity에 데이터 생성

		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student); // student entity에 데이터 생성
	}

	public void someOperationToUnderStandPersistenceContext() {
		// Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		// Persistence Context (student)

		// Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		// Persistence Context (student, passport)

		// Database Operation 3 - update passport
		passport.setNumber("E123457");
		// Persistence Context (student, passport++)

		// Database Operation 4 - update student
		student.setName("Ranga - updated");
		// Persistence Context (student++, passport++)
	}

	public void insertHardCodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 Steps");
		em.persist(student);
		em.persist(course);

		student.addCourses(course);
		course.addStudent(student);

		// owning side에만 persist함
		// 지피티는 영속 상태인 엔티티는 변경 추적이 가능해서 중복 호출 안해도 된대.. 주석으로 바꾸고 실행해봤는데 리얼 없어도 되긴 하네
		em.persist(student);
	}

	public void insertStudentAndCourse(Student student, Course course) {
		student.addCourses(course);
		course.addStudent(student);

		em.persist(student);
		em.persist(course);
	}

}
