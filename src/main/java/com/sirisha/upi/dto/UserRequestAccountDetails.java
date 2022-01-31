package com.sirisha.upi.dto;

public class UserRequestAccountDetails {
	private String userName;
	
	private long accountNumber;
	
	private long amount;

	
	public UserRequestAccountDetails(String userName, long accountNumber, long amount) {
		super();
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public UserRequestAccountDetails() {
		super();
	}

	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	

}
