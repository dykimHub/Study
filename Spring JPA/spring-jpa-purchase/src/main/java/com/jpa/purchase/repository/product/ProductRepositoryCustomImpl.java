package com.jpa.purchase.repository.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jpa.purchase.entity.QProduct;
import com.jpa.purchase.entity.QUser;
import com.jpa.purchase.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public ProductRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
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
	public List<User> findProductByUser(Long productId) {
		QUser user = QUser.user;
		QProduct product =  QProduct.product;
		
		List<User> users = queryFactory
	            .selectFrom(user)
	            .join(user.products, product)
	            .where(product.id.eq(productId))
	            .fetch();
		
		return users;
	}

			

}
