package com.jpa.user.repository.product;

import java.util.List;

import com.jpa.user.entity.User;

public interface ProductRepositoryCustom {
	
	List<User> findProductByUser(Long productId);

}
