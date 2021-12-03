package com.sirisha.upi.service.impl;

import java.util.List;

import com.sirisha.upi.dto.FundTransferDto;
import com.sirisha.upi.exception.RecordsNotFoundException;
import com.sirisha.upi.model.UserFundTransfer;

public interface UserFundTransferService {

	public abstract UserFundTransfer addFund(FundTransferDto fundTransferDto);

	public abstract List<FundTransferDto> getMonthlyStatements(int  year,int month) throws RecordsNotFoundException;

	public abstract List<FundTransferDto> getHistoryDetails();

	

	

}
