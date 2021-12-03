package com.googlepay.service;

import java.util.List;

import com.googlepay.dto.StatementsResponseDto;

public interface StatementsService {

	List<StatementsResponseDto> getAllStatements(String phoneNumber);

}
