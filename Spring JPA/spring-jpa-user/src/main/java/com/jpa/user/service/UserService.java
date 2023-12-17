package com.jpa.user.service;

import java.util.List;

import com.jpa.user.dto.UserDto;
import com.jpa.user.entity.User;

public interface UserService {

	List<User> getUserList();

	int registUser(UserDto userDto);

	User getUserById(int id);

}
