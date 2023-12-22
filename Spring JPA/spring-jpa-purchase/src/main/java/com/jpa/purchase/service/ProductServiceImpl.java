package com.jpa.purchase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.repository.product.ProductRepository;

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
