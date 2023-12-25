package com.jpa.purchase.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.ActiveProfiles;

import com.jpa.purchase.SpringJpaPurchaseApplication;
import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.repository.product.ProductRepository;
import com.jpa.purchase.repository.user.UserRepository;
import com.jpa.purchase.service.ProductService;

import jakarta.transaction.Transactional;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringJpaPurchaseApplication.class)
public class ProductRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Order(1)
	public void registProduct() {
		
		logger.info("Before Product Save -> {}", productRepository.count());
		
		Product product = Product.builder()
				.name("uniform(AwayKit)")
				.price(BigDecimal.valueOf(100000))
				.build();
		
		productRepository.save(product);
		
		logger.info("After Product Save -> {}", productRepository.count());
	}
	
	@Test
	public void deleteProduct() {
		
		Optional<Product> product = productRepository.findById(2L);

		productRepository.deleteById(product.get().getId());

		assertFalse(productRepository.findById(2L).isPresent());

	}
	
	@Test
	public void updateProduct() {
		
		Product originalProduct = productRepository.findById(1L).orElse(null);
		
		logger.info("Before Product Update -> {}", originalProduct.getPrice());
		
		Product newProduct = Product.builder()
				.name(originalProduct.getName())
				.price(BigDecimal.valueOf(70000))
				.build();
		
		productRepository.updateProduct(1L, newProduct);
		
		Product updatedProduct = productRepository.findById(1L).orElse(null);

		logger.info("After Product Update -> {}", updatedProduct.getPrice());
		
	}
	
	@Test
	@Order(2)
	public void getProductByName() {
		List<Product> products = productRepository.findProductByName("uniform");
				
		// products.forEach(product -> logger.info("Uniforms: {}", product.getName()));

		// 한줄에 출력하는 법
		logger.info("Uniforms -> {}", products.stream().map(p -> p.getName()).collect(Collectors.toList()));
	
	}

}
