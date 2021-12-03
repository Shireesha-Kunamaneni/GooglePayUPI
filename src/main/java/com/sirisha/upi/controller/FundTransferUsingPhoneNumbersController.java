package com.sirisha.upi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirisha.upi.dto.FundTransferUsingPhoneNumbersDto;
import com.sirisha.upi.service.impl.FundTransferUsingPhoneNumbersService;

@RestController
@RequestMapping("/FundTransferUsingPhoneNumbers")
public class FundTransferUsingPhoneNumbersController {
	
	@Autowired
	private FundTransferUsingPhoneNumbersService fundTransferUsingPhoneNumbersService;
	
	@PostMapping("/fundTransfer")
	public String transferAmount(@Valid @RequestBody FundTransferUsingPhoneNumbersDto FundTransferUsingPhoneNumbersDto) {
		return fundTransferUsingPhoneNumbersService.transferAmount(FundTransferUsingPhoneNumbersDto);
		
	}

}
