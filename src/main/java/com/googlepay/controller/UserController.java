package com.googlepay.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlepay.dto.UserRequestDto;
import com.googlepay.service.UserService;
import com.googlepay.controller.UserController;
//import com.googlepay.dto.UserRequestDetails;


@RestController
@RequestMapping("users")
@Validated

public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public String saveUser(@Valid @RequestBody UserRequestDto userRequestDto ) {
		return userService.saveUser(userRequestDto);
	}
	
	

}
