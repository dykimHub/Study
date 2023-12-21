package com.jpa.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa.user.entity.Product;
import com.jpa.user.entity.User;
import com.jpa.user.repository.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public Long registProduct(Product product) {
		return productRepository.save(product).getId();
	}

	@Override
	public List<User> findProductByUser(Long productId) {
		return productRepository.findProductByUser(productId);
	}

	

}
