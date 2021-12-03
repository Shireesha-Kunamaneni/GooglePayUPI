package com.sirisha.upi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirisha.upi.dto.FundTransferDto;
import com.sirisha.upi.model.UserFundTransfer;
import com.sirisha.upi.service.impl.UserFundTransferService;

@RestController
@RequestMapping("/fundTransfers")
public class UserFundTransferController {

	private static final Logger logger = LoggerFactory.getLogger(UserFundTransferController.class);

	@Autowired
	private UserFundTransferService userFundTransferService;

	@PostMapping
	public UserFundTransfer addFund(@Valid @RequestBody FundTransferDto fundTransferDto) {
		logger.info("amount is tranfered");

		return userFundTransferService.addFund(fundTransferDto);

	}

}
