package com.nihal.bankingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nihal.bankingapp.dto.AccountDto;
import com.nihal.bankingapp.entity.Account;
import com.nihal.bankingapp.exception.AccountNotFoundException;
import com.nihal.bankingapp.exception.GlobalExceptionHandler;
import com.nihal.bankingapp.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountRepository accountRepository;

	@Test
	void testGetAllAccounts() {
		// Arrange
		Account account = new Account();
		account.setAccountNumber(101L);
		account.setAccountHolderName("Nihal");
		account.setBalance(5000.0);

		when(accountRepository.findAll()).thenReturn(List.of(account));

		// Act
		List<AccountDto> result = accountService.getAllAccounts();

		// Assert
		assertEquals(1, result.size());
		assertEquals("Nihal", result.get(0).getAccountHolderName());
	}

	@Test
	void testCreateAccount() {
		// Arrange
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(9099L);
		accountDto.setAccountHolderName("Papa");
		accountDto.setBalance(10000.0);

		Account account = new Account();
		account.setAccountNumber(9099L);
		account.setAccountHolderName("Papa");
		account.setBalance(10000.0);

		when(accountRepository.save(any(Account.class))).thenReturn(account);
		// Act
		AccountDto result = accountService.createAccount(accountDto);

		// Assert
		assertEquals(9099L, result.getAccountNumber());
		assertEquals("Papa", result.getAccountHolderName());
		assertEquals(10000.0, result.getBalance());
	}

	@Test
	void testGetAccount() throws AccountNotFoundException {
	    // Arrange
	    Account account = new Account();
	    account.setAccountNumber(101L);
	    account.setAccountHolderName("Nihal");
	    account.setBalance(5000.0);

	    when(accountRepository.findById(101L)).thenReturn(Optional.of(account));

	    // Act
	 // Act
	    AccountDto result = accountService.getAccount(101L);

	    // Assert
	    assertEquals(101L, result.getAccountNumber());
	    assertEquals("Nihal", result.getAccountHolderName());
	}

}