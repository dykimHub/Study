package com.jpa.purchase.repository.product;

import java.util.List;

import com.jpa.purchase.entity.User;

public interface ProductRepositoryCustom {
	
	List<User> findProductByUser(Long productId);

}
