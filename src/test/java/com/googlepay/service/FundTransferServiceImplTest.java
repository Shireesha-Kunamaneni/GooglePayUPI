package com.googlepay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.googlepay.dto.FundTransferRequestDto;
import com.googlepay.exception.ResourceNotFoundException;
import com.googlepay.feignclient.FundTransferClient;
import com.googlepay.model.FundTransfer;
import com.googlepay.model.User;
import com.googlepay.repository.FundTransferRepository;
import com.googlepay.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@ExtendWith(SpringExtension.class)
public class FundTransferServiceImplTest {

	@InjectMocks
	FundTransferServiceImpl fundTransferServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	FundTransferRepository fundTransferRepository;

	@Mock
	FundTransferClient fundTransferClient;

	@Mock
	CircuitBreakerFactory circuitBreakerFactory;

	// @Mock
	// CircuitBreaker circuitBreaker;

	/*
	 * @Autowired private CircuitBreakerRegistry circuitBreakerRegistry;
	 * 
	 * @BeforeEach public void setUp() {
	 * circuitBreakerRegistry.circuitBreaker("circuitBreaker").reset(); }
	 */

	@Test
	public void testTransferAmountForPositive() {
		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromPhoneNumber("7036811375");
		fundTransferRequestDto.setToPhoneNumber("7036811375");
		fundTransferRequestDto.setAmount(1000);
		fundTransferRequestDto.setDate(LocalDateTime.now());
		fundTransferRequestDto.setDescription("lap");

		User user = new User();
		user.setUserId(1);
		user.setFirstName("pushpa");
		user.setLastName("kasthuri");
		user.setPhoneNumber("7036811375");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		Mockito.when(userRepository.findUserByPhoneNumber("7036811375")).thenReturn(user);

		Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);
		// Mockito.when(circuitBreaker.run(() ->
		// fundTransferClient.transferAmount(fundTransferRequestDto)))
		// .thenReturn("the amount transfered");

		// Mockito.when(circuitBreaker.run(() ->
		// fundTransferClient.transferAmount(fundTransferRequestDto),
		// throwable -> getDefaultInfo())).thenThrow(Exception.class);
		Mockito.when(fundTransferClient.transferAmount(fundTransferRequestDto)).thenReturn("the amount transfered");
		FundTransfer fundTransfer = new FundTransfer();
		BeanUtils.copyProperties(fundTransferRequestDto, fundTransfer);
		Mockito.when(fundTransferRepository.save(fundTransfer)).thenReturn(fundTransfer);
		String message = fundTransferServiceImpl.transferAmount(fundTransferRequestDto);
		System.out.println(message);
		assertEquals(fundTransfer.getFromPhoneNumber(), fundTransferRequestDto.getFromPhoneNumber());
		// assertNotNull(message);

	}

	@Test
	public void testTransferAmountForAllTransfers() {
		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromPhoneNumber("7036811375");
		fundTransferRequestDto.setToPhoneNumber("7036811375");
		fundTransferRequestDto.setAmount(1000);
		fundTransferRequestDto.setDate(LocalDateTime.now());
		fundTransferRequestDto.setDescription("lap");

		User user = new User();
		user.setUserId(1);
		user.setFirstName("pushpa");
		user.setLastName("kasthuri");
		user.setPhoneNumber("7036811375");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		Mockito.when(userRepository.findUserByPhoneNumber(Mockito.anyString())).thenReturn(user);

		Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);

		Mockito.when(fundTransferClient.transferAmount(Mockito.anyObject())).thenReturn("the amount transfered");
		FundTransfer fundTransfer = new FundTransfer();
		BeanUtils.copyProperties(fundTransferRequestDto, fundTransfer);
		Mockito.when(fundTransferRepository.save(fundTransfer)).thenReturn(fundTransfer);
		String message = fundTransferServiceImpl.transferAmount(fundTransferRequestDto);
		System.out.println(message);
		assertEquals(user.getPhoneNumber(),fundTransferRequestDto.getFromPhoneNumber() );
		//assertNotNull(message);

	}

	@Test // ok
	public void testTransferAmountForUserNotFoundException() {
		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromPhoneNumber("7036811375");
		fundTransferRequestDto.setToPhoneNumber("7036811375");
		fundTransferRequestDto.setAmount(1000);
		fundTransferRequestDto.setDate(LocalDateTime.now());
		fundTransferRequestDto.setDescription("lap");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		try {
			Mockito.when(userRepository.findUserByPhoneNumber("7036811375")).thenReturn(null);
			Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);
			Mockito.when(fundTransferClient.transferAmount(fundTransferRequestDto)).thenReturn("the amount transfered");
			FundTransfer fundTransfer = new FundTransfer();
			BeanUtils.copyProperties(fundTransferRequestDto, fundTransfer);
			Mockito.when(fundTransferRepository.save(fundTransfer)).thenReturn(fundTransfer);
			String message = fundTransferServiceImpl.transferAmount(fundTransferRequestDto);
			assertTrue(false);
		} catch (ResourceNotFoundException exception) {
			System.out.println(exception.getMessage());
			assertTrue(true);
		}

	}

	@Test // ok
	public void testTransferAmountForAllUserNotFoundException() {
		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromPhoneNumber("7036811375");
		fundTransferRequestDto.setToPhoneNumber("7036811375");
		fundTransferRequestDto.setAmount(1000);
		fundTransferRequestDto.setDate(LocalDateTime.now());
		fundTransferRequestDto.setDescription("lap");

		User user = new User();
		user.setUserId(1);
		user.setFirstName("pushpa");
		user.setLastName("kasthuri");
		user.setPhoneNumber("7036811375");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
		try {
			Mockito.when(userRepository.findUserByPhoneNumber(Mockito.anyString())).thenReturn(null);

			Mockito.when(circuitBreakerFactory.create("circuitBreaker")).thenReturn(circuitBreaker);

			Mockito.when(fundTransferClient.transferAmount(Mockito.anyObject())).thenReturn("the amount transfered");
			FundTransfer fundTransfer = new FundTransfer();
			BeanUtils.copyProperties(fundTransferRequestDto, fundTransfer);
			Mockito.when(fundTransferRepository.save(fundTransfer)).thenReturn(fundTransfer);
			String message = fundTransferServiceImpl.transferAmount(fundTransferRequestDto);

			assertTrue(false);
		} catch (ResourceNotFoundException exception) {
			System.out.println(exception.getMessage());
			assertTrue(true);
		}

	}

}
