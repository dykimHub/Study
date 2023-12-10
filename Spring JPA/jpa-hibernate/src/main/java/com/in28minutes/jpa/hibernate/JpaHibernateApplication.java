package com.in28minutes.jpa.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;
import com.in28minutes.jpa.hibernate.entity.Student;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Course course = repository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		//repository.deleteById(10001L);
//		repository.save(new Course("Microservices in 100 Steps"));
//		repository.playWithEntityManager();

//		studentRepository.saveStudentWithPassport();

//		courseRepository.addHardcodedReviewsForCourse();

//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "Great Hands-on Stuff"));
//		reviews.add(new Review("5", "Hatsoff"));
//		courseRepository.addReviewsForCourse(10003L, reviews);

//		studentRepository.insertHardCodedStudentAndCourse();
		studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps"));

	}

}
