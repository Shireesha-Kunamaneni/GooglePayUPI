package com.googlepay.service;

import javax.validation.Valid;

import com.googlepay.dto.FundTransferRequestDto;

public interface FundTransferService {

	String transferAmount(@Valid FundTransferRequestDto fundTransferRequestDto) ;

}
