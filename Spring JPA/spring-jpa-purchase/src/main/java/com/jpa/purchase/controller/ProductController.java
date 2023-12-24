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

import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.dto.UserDto;
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
	public ResponseEntity<?> getProductList() {

		try {
			List<ProductDto> products = productService.getProductList();
			return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/product")
	@Operation(summary = "상품 등록")
	public ResponseEntity<?> registProduct(@RequestBody ProductDto productDto) {

		try {
			Long result = productService.registProduct(productDto);
			return new ResponseEntity<Long>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/product/delete/{id}")
	@Operation(summary = "상품 삭제")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

		try {
			productService.deleteProduct(id);
			// 반환값 void라서 임의로 설정
			return new ResponseEntity<Integer>(1, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/product/update/{id}")
	@Operation(summary = "상품 수정")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id) {

		try {
			Long result = productService.updateProduct(id, productDto);
			return new ResponseEntity<Long>(result, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/product/{name}")
	@Operation(summary = "상품 찾기(비슷한 이름)")
	public ResponseEntity<?> getProductByName(@PathVariable String name) {

		try {
			List<ProductDto> products = productService.getProductByName(name);
			return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/myusers/{id}")
	@Operation(summary = "상품을 구매한 회원")
	public ResponseEntity<?> getUserByProduct(@PathVariable Long id) {

		try {
			List<UserDto> buyers = productService.getProductByUser(id);
			return new ResponseEntity<List<UserDto>>(buyers, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			return new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
