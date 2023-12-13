package com.in28minutes.jpa.hibernate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	// private String rating;
	// 열거형 집합을 enum을 사용해서 정의하는데 enumtype default인 ordinal은 위치를 기반으로 순위를 매김
	// 그러니까 enum에 two, one, three 이렇게 적어도 two가 가장 작은 값(1)이 됨 그래서 string기반으로 바꿔줘야 함
	@Enumerated(EnumType.STRING)
	private ReviewRating rating;

	private String description;

	// many reviews are associated with one course
	// review is the owning side of relationship
	// 정규화 때문인가? course가 owning이면 review_id 열이 만들어지고 이 칸에 review id 여러 개 들어감
	// 그러나 review가 owning이면 course_id 열이 만들어지고 무조건 course 하나랑만 연결되기 때문에 한 칸에 하나
	// many to one 관계에서는 default가 eager fetch
	@ManyToOne
	private Course course;

	protected Review() {

	}

	public Review(ReviewRating rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", description=" + description + "]";
	}

}
