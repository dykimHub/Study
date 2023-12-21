package com.jpa.user.service;

import java.util.List;

import com.jpa.user.entity.Product;
import com.jpa.user.entity.User;

public interface ProductService {

	Long registProduct(Product product);

	List<User> findProductByUser(Long productId);

}
