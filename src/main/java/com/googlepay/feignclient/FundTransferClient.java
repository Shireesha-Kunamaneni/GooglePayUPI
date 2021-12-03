package com.googlepay.feignclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.googlepay.dto.FundTransferRequestDto;


@FeignClient(name = "http://BANK-SERVICE/bank/FundTransferUsingPhoneNumbers")
public interface FundTransferClient {

	@PostMapping("/fundTransfer")
	public String transferAmount(@Valid @RequestBody FundTransferRequestDto fundTransferRequestDto);

}
