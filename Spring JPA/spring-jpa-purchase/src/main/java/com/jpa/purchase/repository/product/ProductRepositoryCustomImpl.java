package com.jpa.purchase.repository.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jpa.purchase.dto.ProductDto;
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
	@Transactional
	public Long updateProduct(Long id, ProductDto productDto) {
		QProduct product =  QProduct.product;

		 return queryFactory.update(product)
	             .where(product.id.eq(id))
	             .set(product.name, productDto.getName())
	             .set(product.price, productDto.getPrice())
	             .execute();
		 
	}	

}
