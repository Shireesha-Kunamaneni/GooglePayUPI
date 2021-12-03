package com.sirisha.upi.dto;

public class UserAccountDto {

	private long accountNumber;
	
	private int userId;

	

	public UserAccountDto(long accountNumber, int userId) {
		super();
		this.accountNumber = accountNumber;
		this.userId = userId;
	}

	public UserAccountDto() {
		super();
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
