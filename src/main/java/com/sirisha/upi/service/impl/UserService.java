package com.sirisha.upi.service.impl;

import java.util.List;

import javax.validation.Valid;

import com.sirisha.upi.dto.UserAccountDto;
import com.sirisha.upi.dto.UserRequestAccountDetails;
import com.sirisha.upi.dto.UserRequestDetails;
import com.sirisha.upi.model.User;
import com.sirisha.upi.model.UserAccount;

public interface UserService {

	public abstract User saveUser(UserRequestDetails userRequestDetails);

	public abstract List<UserRequestDetails> getAllUsers();

	public abstract boolean deleteUser(int userId);

	public abstract String getUsersByEmail(String email);

	public abstract UserAccount createUserAccount(UserAccountDto userAccountDto);

	public abstract UserAccount getUserAccountNumber(int userId);

	public abstract User updateUser(@Valid User user);

	public abstract UserRequestAccountDetails getUserAccountDetails(int userId);

	public abstract List<UserRequestAccountDetails> getAllAccountDetails();

	public abstract Boolean getUserByPhoneNumber(String phoneNumber);

	public abstract User findUserByPhoneNumber(String fromPhoneNumber);

}
