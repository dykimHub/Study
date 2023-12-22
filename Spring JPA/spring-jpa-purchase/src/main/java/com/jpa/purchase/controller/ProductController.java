package com.jpa.purchase.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;

	@PostMapping("/user")
	@Operation(summary = "상품 등록")
	public ResponseEntity<?> registProduct(@RequestBody Product product) {

		try {
			Long result = productService.registProduct(product);
			return new ResponseEntity<Long>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/user/{productId}")
	@Operation(summary = "상품을 구매한 회원")
	public ResponseEntity<?> getUserAndProduct(@PathVariable Long productId) {
		List<User> buyers = productService.findProductByUser(productId);
		return new ResponseEntity<List<User>>(buyers, HttpStatus.OK);

	}

}
