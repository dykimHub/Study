package com.jpa.purchase.repository.user;

import com.jpa.purchase.dto.UserDto;
import com.jpa.purchase.entity.User;

public interface UserRepositoryCustom {

	Long updateUser(Long id, User user);

}
