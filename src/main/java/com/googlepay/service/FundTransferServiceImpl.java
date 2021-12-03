package com.googlepay.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import com.googlepay.dto.FundTransferRequestDto;
import com.googlepay.exception.ClientFeignException;
import com.googlepay.exception.ResourceNotFoundException;
import com.googlepay.feignclient.FundTransferClient;
import com.googlepay.model.FundTransfer;
import com.googlepay.model.User;
import com.googlepay.repository.FundTransferRepository;
import com.googlepay.repository.UserRepository;

import feign.FeignException;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	private FundTransferClient fundTransferClient;

	@Autowired
	private FundTransferRepository fundTransferRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	@Override
	public String transferAmount(FundTransferRequestDto fundTransferRequestDto) {

		String fromPhoneNumber = fundTransferRequestDto.getFromPhoneNumber();
		String toPhoneNumber = fundTransferRequestDto.getToPhoneNumber();
		User fromUser = userRepository.findUserByPhoneNumber(fromPhoneNumber);
		User toUser = userRepository.findUserByPhoneNumber(toPhoneNumber);
		try {
			if (fromUser != null && toUser != null) {
				CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
				//String message = circuitBreaker.run(() -> fundTransferClient.transferAmount(fundTransferRequestDto),
						//throwable -> getDefaultInfo());
				String message = fundTransferClient.transferAmount(fundTransferRequestDto);
				
				if (message.contains("the amount")) {
					FundTransfer fundTransfer = new FundTransfer();
					BeanUtils.copyProperties(fundTransferRequestDto, fundTransfer);
					LocalDateTime date = LocalDateTime.now();
					fundTransfer.setDate(date);
					FundTransfer fund = fundTransferRepository.save(fundTransfer);
					BeanUtils.copyProperties(fundTransfer, fundTransferRequestDto);
					fundTransferRequestDto.setDate(date);
				}
				return message;

			} else if (fromUser == null) {
				throw new ResourceNotFoundException("the fromuser is not registered with googlepay");
			} else {
				throw new ResourceNotFoundException("the touser is not registered with googlepay");
			}

		} catch (FeignException exception) {
			throw new ClientFeignException(
					"client throws an exception..Please check the input validation..  " + exception.contentUTF8());
		}
	}

	private String getDefaultInfo() {

		return "Bank Service is down,Please try after some time or \n client throws an exception:user have not sufficient amount to transfer.. \n Please check the input validation.. ";
	}

}
