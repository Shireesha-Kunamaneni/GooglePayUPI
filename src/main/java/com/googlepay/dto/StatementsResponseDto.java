package com.googlepay.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatementsResponseDto {
	
	private String fromPhoneNumber;
	
	private String toPhoneNumber;
	
	private long amount;

	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime date;
	
	//private String Credit_Debit;

	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}

	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}

	public String getToPhoneNumber() {
		return toPhoneNumber;
	}

	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/*
	 * public String getCredit_Debit() { return Credit_Debit; }
	 * 
	 * public void setCredit_Debit(String credit_Debit) { Credit_Debit =
	 * credit_Debit; }
	 */

	public StatementsResponseDto(String fromPhoneNumber, String toPhoneNumber, long amount, String description,
			LocalDateTime date) {
		super();
		this.fromPhoneNumber = fromPhoneNumber;
		this.toPhoneNumber = toPhoneNumber;
		this.amount = amount;
		this.description = description;
		this.date = date;
		
	}

	public StatementsResponseDto() {
		super();
	}
	

}
