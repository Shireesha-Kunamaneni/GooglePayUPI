package com.googlepay.service;

//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.googlepay.dto.UserRequestDto;
import com.googlepay.exception.ClientFeignException;
import com.googlepay.exception.ResourceNotFoundException;
import com.googlepay.exception.UserFoundException;
import com.googlepay.feignclient.BankClient;
import com.googlepay.model.User;
import com.googlepay.repository.UserRepository;

import feign.FeignException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BankClient bankClient;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	@Override
	public String saveUser(UserRequestDto userRequestDto) {
		User userRequest = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
		if (userRequest != null) {
			throw new UserFoundException("the user is already registered with googlepay");
		} else {
			try {
				CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
				 Boolean status = circuitBreaker.run(() ->
				 bankClient.getUserByPhoneNumber(userRequestDto.getPhoneNumber()),
				 throwable -> getDefaultInfo());
				status = bankClient.getUserByPhoneNumber(userRequestDto.getPhoneNumber());
				System.out.println(status);
				if (status) {
					User user = new User();
					BeanUtils.copyProperties(userRequestDto, user);
					user = userRepository.save(user);
					return "user is registered with phone number  " + userRequestDto.getPhoneNumber();
				} else {
					throw new ResourceNotFoundException(
							"Bank Service is down. Please try after some time or \n user with phone number "
									+ userRequestDto.getPhoneNumber()
									+ " is not registered with your bank account.Please Check ");
				}

			} catch (FeignException exception) {
				throw new ClientFeignException(
						"client throws an exception..Please check the input validation.." + exception.contentUTF8());
			}
		}
	}

	private Boolean getDefaultInfo() {

		return false;
	}

}
