package com.googlepay.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.googlepay.dto.StatementsResponseDto;
import com.googlepay.exception.StatementsNotFoundException;
import com.googlepay.model.FundTransfer;
import com.googlepay.repository.FundTransferRepository;

@Service
public class StatementsServiceImpl implements StatementsService {

	@Autowired
	private FundTransferRepository fundTransferRepository;

	@Override
	public List<StatementsResponseDto> getAllStatements(String phoneNumber) {

		
		List<FundTransfer> fundTransfer = fundTransferRepository.findByFromPhoneNumber(phoneNumber);
		if(fundTransfer.isEmpty()) {
			throw new StatementsNotFoundException("no history found  for this number");
		}
		else {
		List<StatementsResponseDto> StatementsResponse = fundTransfer.stream().map(this::convertToStatementsResponseDto)
				.collect(Collectors.toList());
		return StatementsResponse;
		}

	}

	private StatementsResponseDto convertToStatementsResponseDto(FundTransfer fundTransfer) {
		StatementsResponseDto statementsResponseDto = new StatementsResponseDto();
		BeanUtils.copyProperties(fundTransfer, statementsResponseDto);

		return statementsResponseDto;

	}
}
