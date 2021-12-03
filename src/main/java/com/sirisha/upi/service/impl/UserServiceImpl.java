package com.sirisha.upi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirisha.upi.dto.UserAccountDto;
import com.sirisha.upi.dto.UserRequestAccountDetails;
import com.sirisha.upi.dto.UserRequestDetails;
import com.sirisha.upi.exception.UserFoundException;
import com.sirisha.upi.model.User;
import com.sirisha.upi.model.UserAccount;
import com.sirisha.upi.repository.UserAccountRepository;
import com.sirisha.upi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User saveUser(UserRequestDetails userRequestDetails) {
		User user = new User();
		String email = userRequestDetails.getEmail();
		User userDetails = userRepository.findByEmail(email);
		if (userDetails == null) {
			BeanUtils.copyProperties(userRequestDetails, user);
			return userRepository.save(user);
		} else {
			throw new UserFoundException("user with " + userRequestDetails.getEmail() + " already exists");
		}

	}

	@Override
	public List<UserRequestDetails> getAllUsers() {

		return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private UserRequestDetails convertToDto(User user) {
		UserRequestDetails userRequestDetails = new UserRequestDetails();
		BeanUtils.copyProperties(user, userRequestDetails);
		return userRequestDetails;

	}

	@Override
	public boolean deleteUser(int userId) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public String getUsersByEmail(String email) {

		User user = userRepository.findByEmail(email);
		if (user != null) {
			long min = 10000000;
			long max = 99999999;
			long number = (long) (Math.random() * (max - min + 1) + min);
			UserAccount userAccountdetails = createUserAccount(new UserAccountDto(number, user.getUserId()));
			System.out.println(user.getUserId());
			//UserAccount userAccount = userAccountRepository.findByAccountNumber(user.getUserId());

			return "account is created. \nYour Account Number is " + userAccountdetails.getAccountNumber() + ".";
		} else {
			return "user with " + email + " not found. Please register yourself";
		}

	}

	@Override
	public UserAccount createUserAccount(UserAccountDto userAccountDto) {
		UserAccount userAccount = new UserAccount();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		// BeanUtils.copyProperties(userAccountDto, userAccount);
		userAccount = modelMapper.map(userAccountDto, UserAccount.class);

		return userAccountRepository.save(userAccount);

	}

	@Override
	public UserAccount getUserAccountNumber(int userId) {

		return userAccountRepository.findByUser_userId(userId);
	}

	@Override
	public User updateUser(@Valid User user) {

		return userRepository.save(user);
	}

	@Override
	public UserRequestAccountDetails getUserAccountDetails(int userId) {
		UserRequestAccountDetails userRequestAccountDetails = new UserRequestAccountDetails();
		UserAccount userAccount = userAccountRepository.findByUser_userId(userId);
		User user = userRepository.findById(userId).get();
		BeanUtils.copyProperties(userAccount, userRequestAccountDetails);
		userRequestAccountDetails.setUserName(user.getFirstName());
		return userRequestAccountDetails;
	}

	@Override
	public List<UserRequestAccountDetails> getAllAccountDetails() {

		return userAccountRepository.findAll().stream().map(this::convertToUserAccountDto).collect(Collectors.toList());
	}

	private UserRequestAccountDetails convertToUserAccountDto(UserAccount userAccount) {
		UserRequestAccountDetails userRequestAccountDetails = new UserRequestAccountDetails();
		User user = userRepository.findById(userAccount.getUser().getUserId()).get();
		BeanUtils.copyProperties(userAccount, userRequestAccountDetails);
		userRequestAccountDetails.setUserName(user.getFirstName());
		return userRequestAccountDetails;

	}

	@Override
	public Boolean getUserByPhoneNumber(String phoneNumber) {
		User user=userRepository.findByPhoneNumber(phoneNumber);
		if(user!=null) {
			UserAccount userAccount=userAccountRepository.findByUser_userId(user.getUserId());	
			if(userAccount!=null) {
				return true;
			}
		}
		
		
		return false;
	}

	@Override
	public User findUserByPhoneNumber(String fromPhoneNumber) {
		
		return userRepository.findByPhoneNumber(fromPhoneNumber);
	}

}
