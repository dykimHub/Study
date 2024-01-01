package com.jpa.purchase.repository.product;

import org.springframework.transaction.annotation.Transactional;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public ProductRepositoryCustomImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	@Transactional
	public Long updateProduct(Long id, Product product) {
		QProduct qproduct =  QProduct.product;

		 return queryFactory.update(qproduct)
	             .where(qproduct.id.eq(id))
	             .set(qproduct.name, product.getName())
	             .set(qproduct.price, product.getPrice())
	             .execute();
		 
	}	

}
