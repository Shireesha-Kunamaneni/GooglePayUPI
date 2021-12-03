package com.sirisha.upi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sirisha.upi.dto.FundTransferDto;
import com.sirisha.upi.exception.RecordsNotFoundException;
import com.sirisha.upi.service.impl.UserFundTransferService;

@RestController
@RequestMapping("/histories")
@Validated
public class TransactionHistoryController {
	
	private static final Logger logger=LoggerFactory.getLogger(TransactionHistoryController.class);
	
	@Autowired
	private UserFundTransferService userFundTransferService;
	
	@GetMapping
	public List<FundTransferDto> getMonthlyStatements(@RequestParam  int year,@RequestParam  int month) throws RecordsNotFoundException{
		logger.info("displaying month statement");
		return userFundTransferService.getMonthlyStatements(year,month);
	}
	
	@GetMapping("/getTotalHistory")
	public List<FundTransferDto> getHistoryDetails(){
		return userFundTransferService.getHistoryDetails();
	}
	

}
