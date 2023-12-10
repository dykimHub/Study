package com.in28minutes.jpa.hibernate.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;

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
		Course course1 = findById(id);
		em.remove(course1);
	}

//	flush, clear, detach, refresh 연습
//	public void playWithEntityManager() {
//		Course course1 = new Course("Web Services in 100 Steps");
//		em.persist(course1);
//		
//		Course course2 = new Course("Angular Js in 100 Steps");
//		em.persist(course2);
//
//		em.flush(); // 영속성 컨텍스트에 있는 거를 지우고 db에 바로 반영
//
//		// em.clear(); // 이거하면 밑에문장 실행X 영속성 컨텍스트를 지우는데 db에 반영을 안하고
//
//		// em.detach(course2); // 준영속성 컨텍스트로 전환-> 영속성 컨텍스트가 제공하는 기능 사용불가(ex.변경감지-> update불가)
//		course1.setName("WebServices in 100 Steps - Updated");
//		course2.setName("Angular Js in 100 Steps - Updated");
//
//		em.refresh(course1); // 해당 entity를 다시 읽어옴 cours1이 다시 Web Services in 100 Steps로 변경
//
//		em.flush();
//	}

	public void playWithEntityManager() {
		Course course1 = new Course("Web services in 100 Steps");
		em.persist(course1);

		Course course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");

	}

	public void addHardcodedReviewsForCourse() {
		// get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {}", course.getReviews());

		// add 2 reviews to it
		Review review1 = new Review("5", "Great Hands-on Stuff");
		Review review2 = new Review("5", "Hatsoff.");
		
		// setting the relationship
		course.addReview(review1);
		review1.setCourse(course);

		course.addReview(review2);
		review2.setCourse(course);

		// save it to the database
		em.persist(review1);
		em.persist(review2);

	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);

		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}

	}

}
