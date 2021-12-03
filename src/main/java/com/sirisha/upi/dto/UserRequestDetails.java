package com.sirisha.upi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequestDetails {

	@NotNull(message="first name should not be empty")
	@Size(min=2,message="first name should be greater than 2 chars")
	private String firstName;

	private String lastName;

	@Min(18)
	private int age;

	private String pan;

	@NotNull(message="phone number should not be empty")
	@Size(min=10,max=10,message="phone number should be 10 numbers")
	@Pattern(regexp="^[0-9]{10}$",message="phone number must contain numbers only")
	private String phoneNumber;

	@Email
	//@Column()
	private String email;

	private String gender;

	public UserRequestDetails(String firstName, String lastName, int age, String pan, String phoneNumber, String email,
			String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.pan = pan;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.gender = gender;
	}

	public UserRequestDetails() {
		super();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
