package com.googlepay.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlepay.dto.FundTransferRequestDto;
import com.googlepay.service.FundTransferService;

@RestController
@RequestMapping("/fundTransfers")
public class FundTransferController {
	
	@Autowired
	private FundTransferService fundTransferService;
	
	@PostMapping("/transferAmount")
	public String transferAmount( @RequestBody @Valid FundTransferRequestDto fundTransferRequestDto) {
		return fundTransferService.transferAmount(fundTransferRequestDto);
	}

}
