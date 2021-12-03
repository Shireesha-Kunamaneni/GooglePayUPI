package com.sirisha.upi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sirisha.upi.dto.UserRequestAccountDetails;
import com.sirisha.upi.dto.UserRequestDetails;
import com.sirisha.upi.model.User;
import com.sirisha.upi.service.impl.UserService;

@RestController
@RequestMapping("users")
@Validated
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping
	public User saveUser(@Valid @RequestBody UserRequestDetails userRequestDetails) {

		return userService.saveUser(userRequestDetails);

	}

	@GetMapping
	public List<UserRequestDetails> getAllUsers() {
		logger.info("Displaying All users");
		return userService.getAllUsers();
	}

	@PutMapping
	public User updateUser(@Valid @RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
		boolean status = userService.deleteUser(userId);
		if (status) {
			return new ResponseEntity<>("User with " + userId + " deleted successsfully", HttpStatus.GONE);
		} else {
			return new ResponseEntity<>("User with " + userId + " does not exists", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/accountCreate")
	public String createUserAccount(@RequestParam @Email String email) {
		// User user = userService.getUsersByEmail(email);
		// System.out.println(user.getEmail());
		/*
		 * if (user != null) { long min = 10000000; long max = 99999999; long number =
		 * (long) (Math.random() * (max - min + 1) + min); UserAccount
		 * userAccountdetails = userService .createUserAccount(new
		 * UserAccountDto(number, user.getUserId())); //
		 * System.out.println(user.getUserId()); UserAccount userAccount =
		 * userService.getUserAccountNumber(user.getUserId());
		 * logger.info("Account created"); return
		 * "account is created. \nYour Account Number is " +
		 * userAccount.getAccountNumber() + "."; } return "user with " + email +
		 * " not found. Please register yourself";
		 */
		return userService.getUsersByEmail(email);

	}

	@GetMapping("/userAccountDetailsById")
	public UserRequestAccountDetails getUserAccountDetails(@RequestParam int userId) {
		return userService.getUserAccountDetails(userId);

	}

	@GetMapping("/getAllAccountDetails")
	public List<UserRequestAccountDetails> getAllAccountDetails() {
		return userService.getAllAccountDetails();
	}
	
	@GetMapping("/{phoneNumber}/user")
	public Boolean getUserByPhoneNumber(@PathVariable String phoneNumber) {
		return userService.getUserByPhoneNumber(phoneNumber);
	}

}
