package com.sirisha.upi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private long accountNumber;

	private long amount = 2000;

	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;

	public UserAccount() {
		super();
	}



	public UserAccount(int id, long accountNumber, long amount, User user) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.user = user;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserAccount(long accountNumber,User user) {
		super();
		this.accountNumber = accountNumber;
		this.user=user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
