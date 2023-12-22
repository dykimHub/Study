package com.jpa.purchase.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.purchase.entity.User;

// jparepository, userrepositorycustom 등을 같이 상속받아서 서비스에서 빈 등록 1개만 하도록 하기
// jparepository에서 save, deleteById findById, findAll, count 등 제공
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	
//	jpql은 객체를 다루므로 테이블 대소문자 맞춰야함 
//	findById는 jparepository에 있는 메서드라 오버라이드 못함
//  findByName은 없어서 jpql로 만듦
	@Query("SELECT u FROM User u WHERE u.name = :name")
	User findByName(String name);

	
}
