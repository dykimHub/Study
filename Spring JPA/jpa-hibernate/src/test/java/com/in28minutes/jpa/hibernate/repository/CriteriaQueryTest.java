package com.in28minutes.jpa.hibernate.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.JpaHibernateApplication;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SpringBootTest(classes = JpaHibernateApplication.class)
//jpql? 테이블이 아닌 엔티티를 대상으로 날리는 jpa의 query문
class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void all_courses() {
		// jpql: select c from course c

		// 쿼리 빌더 만들기
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// from course c를 만드는중
		Root<Course> courseRoot = cq.from(Course.class);

		// select c 하는 중
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);
	}

	@Test
	public void all_courses_having_100Steps() {
		// jpql: select c from course c where name like '%100 Steps'

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		// name like ~ 하는 중
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
		// where ~ 하는 중
		cq.where(like100Steps);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);

	}

	@Test
	public void all_courses_without_students() {
		// jpql: select c from course c where c.students is empty

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		// is empty ~ 하는 중
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		cq.where(studentsIsEmpty);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);

	}

	@Test
	public void join() {
		// jpql: select c from course c join c.student s
		// 학생이 듣는 강의를 출력해라 그래서 중복처리 안하면 학생수만큼 강의수가 나와야 함

//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
//
//		Root<Course> courseRoot = cq.from(Course.class);
//
//		// join ~ 하는 중
//		Join<Object, Object> join = courseRoot.join("students");
//
//		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
//		List<Course> resultList = query.getResultList();
//		logger.info("Typed Query -> {}", resultList);

		// 근데 위에거 결과가 자꾸 [Course[JPA in 50 Steps], Course[Spring Boot in 100 Steps]] 나와서
		// 지피티가 튜플로 받고 multiselect 받아보래서 하니까
		// [Course[JPA in 50 Steps], Course[JPA in 50 Steps], Course[JPA in 50 Steps], Course[Spring Boot in 100 Steps]] 이렇게 나와서 일단 이걸 살림
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();

		Root<Course> courseRoot = cq.from(Course.class);

		Join<Object, Object> join = courseRoot.join("students");

		cq.multiselect(courseRoot);

		TypedQuery<Tuple> query = em.createQuery(cq);
		List<Tuple> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);

	}

	@Test
	public void left_join() {
		// jpql: select c from course c left join c.student s
		// left join이니까 join하는데 모든 강의를 출력해라 학생이 듣지 않아도

//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
//
//		Root<Course> courseRoot = cq.from(Course.class);
//
//		// left join ~ 하는 중
//		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
//		
//		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
//		List<Course> resultList = query.getResultList();
//		logger.info("Typed Query -> {}", resultList);

		// 이거도 마찬가지로 위에 거 하면 중복된거 distinct 처리해서 나오는데
		// 밑에 걸로 하면 [[Course[JPA in 50 Steps]], [Course[JPA in 50 Steps]], [Course[JPA in 50 Steps]], [Course[Spring in 50 Steps]], [Course[Spring Boot in 100 Steps]]]
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();

		Root<Course> courseRoot = cq.from(Course.class);

		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		cq.multiselect(courseRoot);

		TypedQuery<Tuple> query = em.createQuery(cq);
		List<Tuple> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);

	}

}
