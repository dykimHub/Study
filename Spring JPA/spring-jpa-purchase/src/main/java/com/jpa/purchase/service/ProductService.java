package com.jpa.purchase.service;

import java.util.List;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;

public interface ProductService {

	Long registProduct(Product product);

	List<User> findProductByUser(Long productId);

}
