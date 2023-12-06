package com.in28minutes.jpa.hibernate.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.entity.Course;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {

		// id가 없으면 persist 있으면 merge
		if (course.getId() == null) {
			em.persist(course); // insert
		} else {
			em.merge(course); // update
		}

		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		Course course = new Course("Web Services in 100 Steps");
		em.persist(course);
		
		//이 파일에서 실행했을 때
		//transactional은 같은 scope에서는 하나의 과정이라고 인식
		//현재 상태에서는 둘다 insert (의도는 밑문장은 수정)
		//application context에서 실행하면 정상적으로 작동
		course.setName("WebServices in 100 Steps - Updated");
	}

}
