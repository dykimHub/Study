package com.jpa.purchase.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;

public interface ProductService {

	List<ProductDto> getProductList() throws NotFoundException;

	Long registProduct(ProductDto productDto);

	List<UserDto> getProductByUser(Long productId) throws NotFoundException;

	void deleteProduct(Long id) throws NotFoundException;

	Long updateProduct(Long id, ProductDto productDto) throws NotFoundException;

	List<ProductDto> getProductByName(String name) throws NotFoundException;

}
