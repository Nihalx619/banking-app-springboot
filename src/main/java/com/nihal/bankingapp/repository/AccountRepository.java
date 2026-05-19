package com.nihal.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nihal.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
