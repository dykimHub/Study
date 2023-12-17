package com.jpa.user.repository.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpa.user.entity.User;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	// querydlsconfig 의존성 주입
	private final JPAQueryFactory queryFactory;

	// UserRepositoryCustomImpl 클래스가 JPAQueryFactory 타입의 빈을 필요로 하며,
	// 스프링이 이를 자동으로 주입하도록 하는 것을 나타냅니다.
	@Autowired
	public UserRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

}
