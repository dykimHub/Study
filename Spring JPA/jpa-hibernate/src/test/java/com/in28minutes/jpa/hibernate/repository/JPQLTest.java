package com.in28minutes.jpa.hibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.JpaHibernateApplication;
import com.in28minutes.jpa.hibernate.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@SpringBootTest(classes = JpaHibernateApplication.class)
//jpql? 테이블이 아닌 엔티티를 대상으로 날리는 jpa의 query문
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;

	@Test
	public void findById_basic() {
		//List<Course> resultList = em.createQuery("Select c From Course c").getResultList();
		List<Course> resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	public void findById_typed() {
		// 이거 쓰는게 더 좋대
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = 
				// em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
				em.createNamedQuery("query_get_100_Step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c  where name like '%100 Steps'-> {}", resultList);
	}
}