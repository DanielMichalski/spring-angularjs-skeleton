package com.example.livesystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livesystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
