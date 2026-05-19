package com.nihal.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nihal.bankingapp.dto.AccountDto;
import com.nihal.bankingapp.dto.TransferDto;
import com.nihal.bankingapp.entity.Account;
import com.nihal.bankingapp.exception.AccountNotFoundException;
import com.nihal.bankingapp.exception.InsufficientBalanceException;
import com.nihal.bankingapp.service.AccountService;

import jakarta.validation.Valid;
import lombok.Delegate;

@RestController
@RequestMapping("/accounts")

public class AccountController {

	@Autowired
	private AccountService accountService;
	

	@PostMapping("/create")
	public AccountDto createAccount(@Valid @RequestBody AccountDto accountDto) {
		return accountService.createAccount(accountDto);
	}

	@GetMapping("/{accountNumber}")
	public AccountDto getAccount(@PathVariable Long accountNumber) throws AccountNotFoundException {
		return accountService.getAccount(accountNumber);
	}

	@PutMapping("/{accountNumber}/deposit/{amount}")
	public AccountDto updateBalance(@PathVariable Long accountNumber, @PathVariable Double amount) {
		AccountDto accountDto = accountService.deposit(accountNumber, amount);
		return accountDto;

	}
	
	@PutMapping("/{accountNumber}/withdraw/{amount}")
	public AccountDto withdraw(@PathVariable Long accountNumber, @PathVariable Double amount) throws InsufficientBalanceException, AccountNotFoundException {
		AccountDto accountDto = accountService.withdraw(accountNumber, amount);
		return accountDto;

	}
	
	@GetMapping
	public List<AccountDto> getAllAccounts() {
		return accountService.getAllAccounts();
	}
    
	
	@DeleteMapping("/{accountNumber}")
	public AccountDto deleteAccount(@PathVariable Long accountNumber ) throws AccountNotFoundException {
		return accountService.deleteAccount(accountNumber);
	}
	
	@PostMapping("/transfer")
	public String transferMoney (@RequestBody TransferDto transferDto) throws AccountNotFoundException, InsufficientBalanceException {
	  Long fromAccountNumber = 	transferDto.getFromAccountNumber();
	  Long toAccountNumber = transferDto.getToAccountNumber();
	  Double amount = transferDto.getAmount();
	  
	  return accountService.TransferMoney(fromAccountNumber, toAccountNumber, amount);
	  
	}
	
	
}
