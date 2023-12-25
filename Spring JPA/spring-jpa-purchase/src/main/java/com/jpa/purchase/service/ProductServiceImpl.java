 package com.jpa.purchase.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;
import com.jpa.purchase.repository.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public List<ProductDto> getProductList() throws NotFoundException {
		
		List<Product> products = productRepository.findAll();
		
		if(products.isEmpty())
			throw new NotFoundException();
		
		List<ProductDto> productDtos = products.stream()
				.map(p -> ProductDto.builder()
						.id(p.getId())
						.name(p.getName())
						.price(p.getPrice())
						.build())
				.collect(Collectors.toList());
		
		return productDtos;
	}

	@Override
	public Long registProduct(ProductDto productDto) {
		
		Product product = Product.builder()
				.name(productDto.getName())
				.price(productDto.getPrice())
				.build();
		
		return productRepository.save(product).getId();
	}
	
	@Override
	public Long updateProduct(Long id, ProductDto productDto) throws NotFoundException {
		if(productRepository.findById(id).isEmpty())
			throw new NotFoundException();
		
		Product product = Product.builder()
				.name(productDto.getName())
				.price(productDto.getPrice())
				.build();
		
		return productRepository.updateProduct(id, product);
	}


	@Override
	public void deleteProduct(Long id) throws NotFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
		
		productRepository.deleteById(id);

	}

	@Override
	public List<ProductDto> getProductByName(String name) throws NotFoundException {
		
		List<Product> products = productRepository.findProductByName(name);
		
		if(products.isEmpty())
			throw new NotFoundException();
		
		List<ProductDto> productDtos = products.stream()
				.map(p -> ProductDto.builder()
						.id(p.getId())
						.name(p.getName())
						.price(p.getPrice())
						.build())
				.collect(Collectors.toList());
		
		return productDtos;
		
	}
	
	@Override
	public List<UserDto> getProductByUser(Long id) throws NotFoundException {
		
		// 물건이 없는 경우
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException());
		
		List<UserDto> userDtos = product.getUsers().stream()
				.map(u -> UserDto.builder()
						.id(u.getId())
						.name(u.getName())
						.birthDate(u.getBirthDate())
						.build())
				.collect(Collectors.toList());
		
		// 상품을 산 회원이 없는 경우
		if(userDtos.isEmpty())
			throw new NotFoundException();
		
		return userDtos;
		
	}

}
