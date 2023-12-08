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
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@SpringBootTest(classes = JpaHibernateApplication.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_parameters() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
		query.setParameter(1, 10001L); // parameter 순서, parameter
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = ? -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_named_parameters() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
		query.setParameter("id", 10001L); // parameter 이름, parameter
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = :id -> {}", resultList);
	}
	
	@Test
	@Transactional // update 할때 넣어줘야 함 일단 불러와야돼서
	public void native_queries_to_update() {
		// 대량 데이터를 변환할 때(ex. 한 테이블의 모든 행) jpql 사용 불가
		Query query = em.createNativeQuery("Update COURSE set last_updated_date = CURRENT_DATE()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}
	
	
}
