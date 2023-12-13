package com.in28minutes.springdata.jpa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
		@NamedQuery(name = "query_get_100_Step_courses", query = "Select c From Course c where name like '%100 Steps'") })
// @Table(name="Course") // 정확하게 테이블을 매핑하는 데 도움을 주는듯
// 잘 수정되지 않는 데이터(ex.읽기 전용 데이터)는 캐싱을 이용해서 읽어오면 좋음
// application.properties에서 2단계 설정하고 어떤 엔티티에 적용하는 건지 알려야 함
@Cacheable
// soft delete 논리적 삭제; entitymanager.remove or JpaRepository로 delete 함수 실행하면 이거 실행함
// 근데 jpa repository에서 @query로 직접 만들어서 하면 @sqldelete, @preremove 안 불러옴
// 왜냐면 sql이 아니라 hibernate 메커니즘에서 사용하는거라 entitymanager로 건드려야만 불러옴
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
// is_deleted가 false인거를 보여줘 그냥 단순히 append하는거고 hibernate는 이게 뭘 뜻하는지는 모른다
// 이 어노테이션 없이 내가 직접 할라면 em.createNativeQuery("SELECT * FROM COURSE WHERE is_deleted = 0", Course.class);
@SQLRestriction("is_deleted = false")
public class Course {
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

	@Id // primary key
	@GeneratedValue
	private Long id;

	// @Column(name="fullname", nullable=false) // full name 행과 mapping, null가능인 행을
	// notnull로 변경
	private String name;

	// one course can have many reviews
	// owning side 아니여서 mappedby 써야만
	// one to many 는 fetch default가 lazy, eager로 바꾸기 가능
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	// student is owning side of relationship
	// 다대다 관계는 새로운 join entity를 만들기 때문에 owning side doesn't matter
	// 근데 owning side 설정안하면 join entity 2개 만들어짐
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore // 이거 rest api하면 이상하게 출력해서 이 어노테이션 달면 얘는 출력 안함
	private List<Student> students = new ArrayList<>();

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	private boolean isDeleted;
	
	// preremove는 sql db 하고 entity를 맞추기 위해 사용함
	// sqldelete로 테이블을 업뎃했으니까 entity도 같이 업뎃하기 위해 사용
	// 근데 지피티는 이거 안해도 second level 캐시에 is_deleted=true로 반영이 된다나
	// 코드의 가독성을 높여주긴 한디야
	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}

	protected Course() {

	}

	public Course(String name) {
		this.name = name;
	}

	// id는 setter 없어도 됨
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	// course 메서드에서 리뷰 수정 못하도록 setreview 없앰
	public void addReview(Review review) {
		this.reviews.add(review); // 리스트형이라 add 함수 쓸 수 있음
	}

	public void removeReview(Review review) {
		this.reviews.remove(review); // 리스트형이라 remove 함수 쓸 수 있음
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}

}
