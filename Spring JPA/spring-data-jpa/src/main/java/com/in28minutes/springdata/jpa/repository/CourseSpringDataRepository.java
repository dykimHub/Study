package com.in28minutes.springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.in28minutes.springdata.jpa.entity.Course;

//인터페이스로 정의하기
@RepositoryRestResource(path = "courses") // 간단하게 rest 해주는데(ex. detail 링크 알아서 만듦) 권장은 안함
// jparepository에서 상속받아서 쉽게 로직을 짤 수 있음 <entitiy 클래스, 해당 entitiy클래스의 기본키>
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	// 여기서 부터는 커스텀 함수, 인터페이스라 정의만

	// find, read, get 등으로 시작하면 jpa 에서 알아서 select 해준대 대박
	List<Course> findByName(String name);

	List<Course> findByNameAndId(String name, Long id);

	List<Course> countByName(String name);
	
	// 정렬도 한대 와우
	List<Course> findByNameOrderByIdDesc(String name);

	// delete, remove, erase, discard 등으로 시작하면 알아서 삭제 해준대
	List<Course> deleteByName(String name);

	// 근데 이렇게 써야 정확하대
	@Query("Select c From Course c where name like '%100 Steps'")
	List<Course> courseWith100Steps(String name);

	@Query(value = "Select * From Course c where name like '%100 Steps'", nativeQuery = true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	// entity에 정의된 namedQuery 쓴거
	@Query(name = "query_get_100_Step_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
}
