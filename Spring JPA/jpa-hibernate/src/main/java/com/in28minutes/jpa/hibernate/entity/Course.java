package com.in28minutes.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// table 자동 생성 돼서 sql 파일에 createtable~ 할 필요 없음
@Entity
//namedquery가 여러개 일때
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
		@NamedQuery(name = "query_get_100_Step_courses", query = "Select c From Course c where name like '%100 Steps'") })
// @Table(name="Course") // 정확하게 테이블을 매핑하는 데 도움을 주는듯
public class Course {

	@Id // primary key
	@GeneratedValue
	private Long id;

	// @Column(name="fullname", nullable=false) // full name 행과 mapping, null가능인 행을 notnull로 변경
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
	private List<Student> students = new ArrayList<>();

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

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
