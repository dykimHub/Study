package com.jpa.purchase.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jpa.purchase.dto.ProductDto;
import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.Product;
import com.jpa.purchase.entity.User;

public interface UserService {

	List<UserDto> getUserList() throws NotFoundException;

	Long registUser(UserDto userDto);

	Long updateUser(Long id, UserDto userDto) throws NotFoundException;

	void deleteUser(Long id) throws NotFoundException;

	UserDto getUserByName(String name);

	Long buyProduct(Long userId, Long productId) throws NotFoundException;

	List<ProductDto> getProductByUser(Long id) throws NotFoundException;

}
