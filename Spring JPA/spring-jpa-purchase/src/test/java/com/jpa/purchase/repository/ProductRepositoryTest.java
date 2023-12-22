package com.jpa.purchase.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jpa.purchase.repository.product.ProductRepository;

public class ProductRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductRepository productReoisitory;
	
	

}
