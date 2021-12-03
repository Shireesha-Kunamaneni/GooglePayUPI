package com.googlepay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.googlepay.dto.UserRequestDto;
import com.googlepay.exception.ResourceNotFoundException;
import com.googlepay.exception.UserFoundException;
import com.googlepay.feignclient.BankClient;
import com.googlepay.model.User;
import com.googlepay.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	CircuitBreakerFactory circuitBreakerFactory;

	@Mock
	BankClient bankClient;

	@Test
	public void testSaveUserForPositive() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setFirstName("Ganesh");
		userRequestDto.setLastName("K");
		userRequestDto.setPhoneNumber("9652692085");
		User user = new User();
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		System.out.println(user.getFirstName());
		Mockito.when(userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(null);
		Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);
		// sMockito.when(circuitBreaker.run(null)).thenReturn(true);
		Mockito.when(bankClient.getUserByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(true);
		String message = userServiceImpl.saveUser(userRequestDto);
		assertEquals("Ganesh", userRequestDto.getFirstName());
		// assertTrue(true);
	}

	@Test
	public void testSaveUserForUserFoundException() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setFirstName("Ganesh");
		userRequestDto.setLastName("K");
		userRequestDto.setPhoneNumber("9652692085");
		User user = new User();
		user.setFirstName("Ganesh");
		user.setLastName("K");
		user.setPhoneNumber("9652692085");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		System.out.println(user.getFirstName());
		try {
			Mockito.when(userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(user);
			Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);
			// sMockito.when(circuitBreaker.run(null)).thenReturn(true);
			Mockito.when(bankClient.getUserByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(true);
			String message = userServiceImpl.saveUser(userRequestDto);

			assertTrue(false);
		} catch (UserFoundException exception) {
			System.out.println(exception.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void testSaveUserForAllUserFoundException() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setFirstName("Ganesh");
		userRequestDto.setLastName("K");
		userRequestDto.setPhoneNumber("9652692085");
		User user = new User();
		user.setFirstName("Ganesh");
		user.setLastName("K");
		user.setPhoneNumber("9652692085");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		System.out.println(user.getFirstName());
		try {
			Mockito.when(userRepository.findByPhoneNumber(Mockito.anyString())).thenReturn(user);
			Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);

			Mockito.when(bankClient.getUserByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(true);
			String message = userServiceImpl.saveUser(userRequestDto);

			assertTrue(false);
		} catch (UserFoundException exception) {
			System.out.println(exception.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void testSaveUserForAllNewUsers() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setFirstName("Ganesh");
		userRequestDto.setLastName("K");
		userRequestDto.setPhoneNumber("9652692085");
		User user = new User();

		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		System.out.println(user.getFirstName());
		Mockito.when(userRepository.findByPhoneNumber(Mockito.anyString())).thenReturn(null);
		Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);

		Mockito.when(bankClient.getUserByPhoneNumber(Mockito.anyString())).thenReturn(true);
		String message = userServiceImpl.saveUser(userRequestDto);
		// System.out.println(message);
		
		assertEquals("Ganesh", userRequestDto.getFirstName());

	}

	@Test
	public void testSaveUserForAllNewUsersButNotRegisteredWithBank() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setFirstName("Ganesh");
		userRequestDto.setLastName("K");
		userRequestDto.setPhoneNumber("9652692085");
		User user = new User();
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		System.out.println(user.getFirstName());
		Mockito.when(userRepository.findByPhoneNumber(Mockito.anyString())).thenReturn(null);

		try {
			Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);
			Mockito.when(bankClient.getUserByPhoneNumber((Mockito.anyString()))).thenReturn(false);
			String message = userServiceImpl.saveUser(userRequestDto);

			assertTrue(false);
		} catch (ResourceNotFoundException exception) {
			System.out.println(exception.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void testSaveUserForUserFoundBankException() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setFirstName("Ganesh");
		userRequestDto.setLastName("K");
		userRequestDto.setPhoneNumber("9652692085");
		User user = new User();
		user.setFirstName("Ganesh");
		user.setLastName("K");
		user.setPhoneNumber("9652692085");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		System.out.println(user.getFirstName());
		Mockito.when(userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(null);
		try {
			
			Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);
			Mockito.when(bankClient.getUserByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(false);
			String message = userServiceImpl.saveUser(userRequestDto);

			assertTrue(false);
		} 
		catch (ResourceNotFoundException exception) {
			System.out.println(exception.getMessage());
			assertTrue(true);
		}
	}
}
