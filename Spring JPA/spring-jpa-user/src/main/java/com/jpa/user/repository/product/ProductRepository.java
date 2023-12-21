package com.jpa.user.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.user.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
