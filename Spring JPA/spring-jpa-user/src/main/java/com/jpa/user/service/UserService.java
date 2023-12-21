package com.jpa.user.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.Product;
import com.jpa.user.entity.User;

public interface UserService {

	List<User> getUserList();

	Long registUser(UserDto userDto);

	Long updateUser(Long id, UserDto userDto) throws NotFoundException;

	void deleteUser(Long id) throws NotFoundException;

	User getUserByName(String name);

	Long buyProduct(Long userId, Long productId) throws NotFoundException;

}
