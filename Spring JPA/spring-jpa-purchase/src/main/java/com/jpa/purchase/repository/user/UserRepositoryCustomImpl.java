package com.jpa.purchase.repository.user;

import org.springframework.transaction.annotation.Transactional;

import com.jpa.purchase.entity.QUser;
import com.jpa.purchase.entity.User;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	// querydlsconfig 의존성 주입
	// 이미 빈으로 등록되어 있어서 autowired 안해도 됨
	private final JPAQueryFactory queryFactory;

	// 생성자
	public UserRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	// QClass 못받아오면 run as -> maven install 실행시키고 성공하면
	// target-> generated-sources-> annotations 밑에 QClass 있을 건데
	// build path -> source -> add Folder에서 target-> generated-sources-> annotations 클릭
	// generated-test-sources -> annotations도 클릭하고 add 눌러눌러

	// ***query dsl 기본 문법***
	// select: selectFrom -> where eq.and(or) 복수조건
	// 테이블 2개 이상: query.from(qentity1, qentity2)
	// order by: selectFrom -> orderBy(customer.lastName.asc()) -> fetch()
	// subquery: QDepartment department = QDepartment.department;
	// QDepartment d = new QDepartment("d");
	// subquery: queryFactory.selectFrom(department)
	//           .where(department.size.eq(JPAExpressions.select(d.size.max()).from(d)))
	//           .fetch()

	@Override
	@Transactional // 안붙이면 오류 남 실행 오류나면 롤백해야돼서
	public Long updateUser(Long id, User updatedUser) {
		QUser quser = QUser.user;

		return (queryFactory.update(quser)
				.where(quser.id.eq(id))
				.set(quser.password, updatedUser.getPassword())
				.set(quser.name, updatedUser.getName())
				.set(quser.birthDate, updatedUser.getBirthDate()))
				.execute(); // 영향받은 행 개수 반환
			//	.fetch(); //  영향받은 행 값을 반환
	}			

}
