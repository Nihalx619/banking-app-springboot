package com.nihal.bankingapp.exception;

public class InsufficientBalanceException extends Exception {
	 public InsufficientBalanceException (String message){
			super(message);
		}
}
