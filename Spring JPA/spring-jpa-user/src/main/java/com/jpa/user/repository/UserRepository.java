package com.jpa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
}
