package com.sirisha.upi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirisha.upi.dto.FundTransferDto;
import com.sirisha.upi.exception.BalanceInsufficientException;
import com.sirisha.upi.exception.RecordsNotFoundException;
import com.sirisha.upi.exception.ResourceNotFoundException;
import com.sirisha.upi.model.UserAccount;
import com.sirisha.upi.model.UserFundTransfer;
import com.sirisha.upi.repository.UserAccountRepository;
import com.sirisha.upi.repository.UserFundTransferRepository;

@Service
public class UserFundTransferServiceImpl implements UserFundTransferService {

	@Autowired
	private UserFundTransferRepository userFundTransferRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserFundTransfer addFund(FundTransferDto fundTransferDto) {
		UserAccount fromAccount = userAccountRepository.findByAccountNumber(fundTransferDto.getFromAccountNumber());
		UserAccount toAccount = userAccountRepository.findByAccountNumber(fundTransferDto.getToAccountNumber());
		if (fromAccount != null && toAccount != null) {

			if (fromAccount.getAmount() > fundTransferDto.getAmount()) {
				fromAccount.setAmount(fromAccount.getAmount() - fundTransferDto.getAmount());
				toAccount.setAmount(toAccount.getAmount() + fundTransferDto.getAmount());
				userAccountRepository.save(fromAccount);
				userAccountRepository.save(toAccount);

			} else {
				throw new BalanceInsufficientException("user have not sufficient amount to transfer");
			}

		} else {
			throw new ResourceNotFoundException("either fromaccount or toaccount is not available.please check it");

		}
		UserFundTransfer userFundTransfer = new UserFundTransfer();
		BeanUtils.copyProperties(fundTransferDto, userFundTransfer);
		return userFundTransferRepository.save(
				new UserFundTransfer(0, userFundTransfer.getFromAccountNumber(), userFundTransfer.getToAccountNumber(),
						userFundTransfer.getAmount(), userFundTransfer.getDescription(), LocalDateTime.now()));

	}

	@Override
	public List<FundTransferDto> getMonthlyStatements(int year, int month) throws RecordsNotFoundException {
		List<UserFundTransfer> userFundTransfer = userFundTransferRepository.findByDateContains(year, month);

		List<FundTransferDto> transactionDetails = userFundTransfer.stream().map(this::convertToDto)
				.collect(Collectors.toList());
		if (transactionDetails.isEmpty()) {
			 throw new RecordsNotFoundException("no records found in this date " + year + " " + month);
		}
		return transactionDetails;
	}

	@Override
	public List<FundTransferDto> getHistoryDetails() {

		return userFundTransferRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private FundTransferDto convertToDto(UserFundTransfer userFundTransfer) {
		FundTransferDto fundTransferDto = new FundTransferDto();
		BeanUtils.copyProperties(userFundTransfer, fundTransferDto);
		return fundTransferDto;

	}

}
