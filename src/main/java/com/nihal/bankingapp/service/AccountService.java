package com.nihal.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihal.bankingapp.dto.AccountDto;
import com.nihal.bankingapp.entity.Account;
import com.nihal.bankingapp.repository.AccountRepository;
import com.nihal.bankingapp.exception.*;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public AccountDto createAccount(AccountDto accountDto) {
		Account account = mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account); // capture saved entity
		accountDto = mapToAccountDto(savedAccount);
		return accountDto;
	}

	public AccountDto getAccount(Long id) throws AccountNotFoundException {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException("account not found with id: " + id));
		AccountDto accountDto = mapToAccountDto(account);
		return accountDto;
	}

	public AccountDto deposit(Long accountNumber, double amount) {

		Account account = accountRepository.findById(accountNumber).orElse(null);
		account.setBalance(account.getBalance() + amount);
		Account savedAccount = accountRepository.save(account);
		AccountDto accountDto = mapToAccountDto(savedAccount);
		return accountDto;
	}

	public AccountDto withdraw(Long accountNumber, double amount)
			throws InsufficientBalanceException, AccountNotFoundException {

		Account account = accountRepository.findById(accountNumber).orElseThrow(
				() -> new AccountNotFoundException("account not found with account number: " + accountNumber));
		Double balance = account.getBalance();
		if (amount > balance) {

			throw new InsufficientBalanceException("not enough balance");
		}
		account.setBalance(balance - amount);

		Account savedAccount = accountRepository.save(account);
		AccountDto accountDto = mapToAccountDto(savedAccount);
		return accountDto;
	}

	public List<AccountDto> getAllAccounts() {
		List<Account> allAccounts = accountRepository.findAll();
		List<AccountDto> allAccountsDto = allAccounts.stream().map(account -> mapToAccountDto(account))
				.collect(Collectors.toList());
		return allAccountsDto;
	}

	public AccountDto deleteAccount(Long accountNumber) throws AccountNotFoundException {
		AccountDto accountDto = getAccount(accountNumber);
		accountRepository.deleteById(accountNumber);
		return accountDto;
	}
	
	
	public String TransferMoney(Long fromAccountNumber , Long toAccountNumber , double amount) throws AccountNotFoundException, InsufficientBalanceException{
		AccountDto fromAccountDto = getAccount(fromAccountNumber);
		AccountDto toAccountDto = getAccount(toAccountNumber);
		
		if(fromAccountDto.getBalance() < amount){
			throw new InsufficientBalanceException("not enough balance");
		}
		
		
		double bal1 = fromAccountDto.getBalance() - amount;
		double bal2 = toAccountDto.getBalance() + amount;
		
		fromAccountDto.setBalance(bal1);
		toAccountDto.setBalance(bal2);
		
		Account fromAccount = mapToAccount(fromAccountDto);
		Account toAccount = mapToAccount(toAccountDto);
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		
		return "Transaction was succesfull";
	}

	 private AccountDto mapToAccountDto(Account account) {
		AccountDto dto = new AccountDto();
		dto.setAccountHolderName(account.getAccountHolderName());
		dto.setAccountNumber(account.getAccountNumber());
		dto.setBalance(account.getBalance());
		return dto;
	}

	private Account mapToAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setAccountHolderName(accountDto.getAccountHolderName());
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setBalance(accountDto.getBalance());
		return account;
	}

}
