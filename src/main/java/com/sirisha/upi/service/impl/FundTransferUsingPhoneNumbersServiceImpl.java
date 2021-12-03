package com.sirisha.upi.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirisha.upi.dto.FundTransferDto;
import com.sirisha.upi.dto.FundTransferUsingPhoneNumbersDto;
import com.sirisha.upi.exception.ResourceNotFoundException;
import com.sirisha.upi.model.User;
import com.sirisha.upi.model.UserAccount;
import com.sirisha.upi.model.UserFundTransfer;
import com.sirisha.upi.repository.UserAccountRepository;

@Service
public class FundTransferUsingPhoneNumbersServiceImpl implements FundTransferUsingPhoneNumbersService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private UserFundTransferService userFundTransferService;

	@Override
	public String transferAmount(
			
			FundTransferUsingPhoneNumbersDto fundTransferUsingPhoneNumbersDto) {
		
		User fromUser=userService.findUserByPhoneNumber(fundTransferUsingPhoneNumbersDto.getFromPhoneNumber());
		User toUser=userService.findUserByPhoneNumber(fundTransferUsingPhoneNumbersDto.getToPhoneNumber());
		if(fromUser!=null && toUser!=null) {
			UserAccount fromUserAccount=userAccountRepository.findByUser_userId(fromUser.getUserId());
			UserAccount toUserAccount=userAccountRepository.findByUser_userId(toUser.getUserId());
			FundTransferDto fundTransferDto=new FundTransferDto();
			fundTransferDto.setFromAccountNumber(fromUserAccount.getAccountNumber());
			fundTransferDto.setToAccountNumber(toUserAccount.getAccountNumber());
			fundTransferDto.setAmount(fundTransferUsingPhoneNumbersDto.getAmount());
			fundTransferDto.setDescription(fundTransferUsingPhoneNumbersDto.getDescription());
			//fundTransferUsingPhoneNumbersDto.setDate(date);
			fundTransferDto.setDate(LocalDateTime.now());
			UserFundTransfer userFundTransfer=userFundTransferService.addFund(fundTransferDto);
			return "the amount "+fundTransferUsingPhoneNumbersDto.getAmount()+" is transferred from  "+fundTransferUsingPhoneNumbersDto.getFromPhoneNumber()+"to  "+fundTransferUsingPhoneNumbersDto.getToPhoneNumber();
			
		}
		else if(fromUser==null) {
			throw new ResourceNotFoundException(" fromuser not found.Please check");
		}
		else if(toUser==null) {
			throw new ResourceNotFoundException(" touser not found.Please check");
		}
		else {
			throw new ResourceNotFoundException(" users are not found.Please check");
		}
		
		
	}

}
