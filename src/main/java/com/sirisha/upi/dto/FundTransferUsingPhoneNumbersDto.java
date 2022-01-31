package com.sirisha.upi.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FundTransferUsingPhoneNumbersDto {

	@NotNull(message = "phone number should not be empty")
	@Size(min = 10, max = 10, message = "phone number should be 10 numbers")
	@Pattern(regexp = "^[0-9]{10}$", message = "phone number must contain numbers only")
	private String fromPhoneNumber;

	@NotNull(message = "phone number should not be empty")
	@Size(min = 10, max = 10, message = "phone number should be 10 numbers")
	@Pattern(regexp = "^[0-9]{10}$", message = "phone number must contain numbers only")
	private String toPhoneNumber;

	
	private long amount;

	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime date;

	public FundTransferUsingPhoneNumbersDto(String fromPhoneNumber, String toPhoneNumber, long amount,
			String description, LocalDateTime date) {
		super();
		this.fromPhoneNumber = fromPhoneNumber;
		this.toPhoneNumber = toPhoneNumber;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

	public FundTransferUsingPhoneNumbersDto() {
		super();
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
