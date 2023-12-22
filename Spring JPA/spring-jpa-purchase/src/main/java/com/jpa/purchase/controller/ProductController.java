package com.jpa.purchase.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products")
	@Operation(summary = "상품 목록")
	public ResponseEntity<?> getUserList() {
		List<Product> products = productService.getProductList();

		if (products == null || products.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@PostMapping("/product")
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

		if (buyers == null || buyers.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(buyers, HttpStatus.OK);

	}

	@DeleteMapping("/product/delete/{id}")
	@Operation(summary = "상품 삭제")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		try {
			productService.deleteProduct(id);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/product/update/{id}")
	@Operation(summary = "상품 수정")
	public ResponseEntity<?> updateUser(@RequestBody Product product, @PathVariable Long id) {

		try {
			Long result = productService.updateProduct(id, product);
			return new ResponseEntity<Long>(result, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/product/{name}")
	@Operation(summary = "상품 찾기(이름)")
	public ResponseEntity<?> getProductByName(@PathVariable String name) {
		List<Product> products = productService.getProductByName(name);

		if (products == null || products.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

}
