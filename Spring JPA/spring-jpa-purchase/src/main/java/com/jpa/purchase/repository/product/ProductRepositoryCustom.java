package com.jpa.purchase.repository.product;

import java.util.List;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;

public interface ProductRepositoryCustom {
	
	List<User> findProductNUser(Long productId);

	Long updateProduct(Long id, Product updatedProduct);

}
