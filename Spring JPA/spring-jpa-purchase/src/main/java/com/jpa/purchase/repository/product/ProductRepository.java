package com.jpa.purchase.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.purchase.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')")
	List<Product> findProductByName(String name);

}
