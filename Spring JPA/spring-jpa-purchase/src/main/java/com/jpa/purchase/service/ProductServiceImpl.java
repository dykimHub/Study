package com.jpa.purchase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	@Override
	public Long registProduct(Product product) {
		return productRepository.save(product).getId();
	}

	@Override
	public List<User> findProductByUser(Long productId) {
		return productRepository.findById(productId).orElse(null).getUsers();
	}

	@Override
	public void deleteProduct(Long id) throws NotFoundException {
		Optional<Product> product = productRepository.findById(id);

		if (product.isEmpty())
			throw new NotFoundException();
		
		productRepository.deleteById(id);

	}

	@Override
	public Long updateProduct(Long id, Product product) throws NotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isEmpty())
			throw new NotFoundException();
		
		return productRepository.updateProduct(id, product);
	}

	@Override
	public List<Product> getProductByName(String name) {
		return productRepository.getProductByName(name);
		
	}

}
