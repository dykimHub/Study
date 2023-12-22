package com.jpa.purchase.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;

public interface ProductService {

	List<Product> getProductList();

	Long registProduct(Product product);

	List<User> findProductByUser(Long productId);

	void deleteProduct(Long id) throws NotFoundException;

	Long updateProduct(Long id, Product product) throws NotFoundException;

	List<Product> getProductByName(String name);

}
