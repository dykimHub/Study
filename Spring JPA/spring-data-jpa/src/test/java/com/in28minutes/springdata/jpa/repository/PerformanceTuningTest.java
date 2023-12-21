package com.in28minutes.springdata.jpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.springdata.jpa.SpringDataJpaApplication;
import com.in28minutes.springdata.jpa.entity.Course;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;

@SpringBootTest(classes = SpringDataJpaApplication.class)
class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	// n+1 problem? orm 객체관계매핑에서의 문제점
	// 첫 번째 조회 (1): 주 객체를 조회합니다. (1번의 쿼리 실행)
	// 연관된 객체들 조회 (N): 주 객체에 연관된 N개의 객체를 조회합니다. (N번의 쿼리 실행)
	@Test
	public void createNPlusOneProblem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();

		for (Course course : courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}

	@Test
	public void solvingNPlusOneProblem_EntityGraph() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		// student 있다고 미리 힌트를 줘서 처음 조회 쿼리를 날릴 때 그 student도 join해서 불러옴
		// student가 있으면 inner join, 없으면 left join
		// lazy fetch에서는 course 하나 조회할 때 student가 있으면 걔를 조회하는 쿼리 2번 씀
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");

		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph).getResultList();

		for (Course course : courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}

	@Test
	public void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class).getResultList();
		for(Course course:courses){
			logger.info("Course -> {} Students -> {}",course, course.getStudents());
		}

		// JOIN FETCH는 매번 사용될 때마다 해당 쿼리에 직접 포함되어야 합니다.
		// EntityGraph는 @NamedEntityGraph를 사용하여 재사용 가능한 그래프를 정의하고, 코드에서 동적으로 생성할 수 있어서 더 유연하게 활용할 수 있습니다.
		// 작은 데이터셋에서는 JOIN FETCH를 사용하여 한 번의 쿼리로 데이터를 로딩하는 것이 효과적일 수 있습니다.
		// 대량의 데이터에서는 JOIN을 사용하여 필요한 부분만 가져오는 것이 성능 향상에 도움이 될 수 있습니다.

	}

}
