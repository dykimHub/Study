package com.jpa.purchase.repository.product;

import java.util.List;

import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;

public interface ProductRepositoryCustom {

	Long updateProduct(Long id, ProductDto productDto);

}
