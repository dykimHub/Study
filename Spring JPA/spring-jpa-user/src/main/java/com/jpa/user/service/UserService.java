package com.jpa.user.service;

import java.util.List;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.User;

public interface UserService {

	List<User> getUserList();

	Long registUser(UserDto userDto);

	Long updateUser(Long id, UserDto userDto);

	boolean deleteUser(Long id);

	User getUserByName(String name);

}
