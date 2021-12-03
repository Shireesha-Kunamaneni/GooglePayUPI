package com.sirisha.upi.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;


public class FundTransferUsingPhoneNumbers {

	
	private int id;

	private String fromPhoneNumber;

	private String toPhoneNumber;

	private long amount;

	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime date;

	public FundTransferUsingPhoneNumbers() {
		super();
	}

	public FundTransferUsingPhoneNumbers(int id, String fromPhoneNumber, String toPhoneNumber, long amount,
			String description, LocalDateTime date) {
		super();
		this.id = id;
		this.fromPhoneNumber = fromPhoneNumber;
		this.toPhoneNumber = toPhoneNumber;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

}
