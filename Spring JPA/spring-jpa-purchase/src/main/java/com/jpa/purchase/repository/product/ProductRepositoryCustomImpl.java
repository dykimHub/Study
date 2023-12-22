package com.jpa.purchase.repository.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.QProduct;
import com.jpa.purchase.entity.QUser;
import com.jpa.purchase.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public ProductRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	
	@Override
	public List<User> findProductNUser(Long productId) {
		QUser user = QUser.user;
		QProduct product =  QProduct.product;
		
		List<User> users = queryFactory.selectFrom(user)
	            .join(user.products, product)
	         // .leftJoin(user.products, product).fetchJoin() // 패치 조인하면 n+1 피함
	            .where(product.id.eq(productId))
	            .fetch();
		
//		List<User> users = queryFactory.selectFrom(user)
//				.join(user.products, product)
//				.on(product.id.eq(productId))
//				.fetch();
		
		return users;
	}
	
	@Override
	@Transactional
	public Long updateProduct(Long id, Product updatedProduct) {
		QProduct product =  QProduct.product;

		 return queryFactory.update(product)
	             .where(product.id.eq(id))
	             .set(product.name, updatedProduct.getName())
	             .set(product.price, updatedProduct.getPrice())
	             .execute();
		 
	}	

}
