package com.jpa.user.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
	
//	 jpql은 객체를 다루므로 테이블 대소문자 맞춰야함 
//	 findById는 jparepository에 있는 메서드라 오버라이드 못함
//	 @Query("SELECT u FROM User u WHERE u.id = :id")
//	 User findById(String id);

	
}
