package com.googlepay.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequestDto {

	@NotNull(message="first name is mandatory")
	@Size(min=2,message="first name should be altest 2 characters")
	private String firstName;

	private String lastName;
	

	@NotNull(message="phone number is mandatory")
	@Size(min=10,max=10,message="phone number should be 10 numbers")
	@Pattern(regexp="^[0-9]{10}$",message="phone number must contain numbers only")
	private String phoneNumber;

	public UserRequestDto() {
		super();
	}

	public UserRequestDto(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
