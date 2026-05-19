package com.nihal.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nihal.bankingapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
