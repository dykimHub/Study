package com.jpa.purchase.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.purchase.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
