package com.googlepay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.googlepay.dto.StatementsResponseDto;
import com.googlepay.exception.StatementsNotFoundException;
import com.googlepay.model.FundTransfer;
import com.googlepay.repository.FundTransferRepository;

@ExtendWith(SpringExtension.class)
public class StatementsServiceImplTest {

	@InjectMocks
	StatementsServiceImpl statementsServiceImpl;

	@Mock
	FundTransferRepository fundTransferRepository;

	@Test
	public void testGetAllStatementsForPositive() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setToPhoneNumber("1234567890");
		fundTransfer.setFromPhoneNumber("7036811375");
		fundTransfer.setAmount(100);
		fundTransfer.setDate(LocalDateTime.now());
		fundTransfer.setDescription("sample");
		FundTransfer fundTransfer1 = new FundTransfer();
		fundTransfer1.setFromPhoneNumber("9652692085");
		fundTransfer1.setToPhoneNumber("7036811375");
		fundTransfer1.setAmount(10);
		fundTransfer1.setDate(LocalDateTime.now());
		fundTransfer1.setDescription("sample1");
		List<FundTransfer> lists = new ArrayList<>();
		lists.add(fundTransfer);
		lists.add(fundTransfer1);
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Direction.DESC, "date"));
		Mockito.when(fundTransferRepository.findByFromPhoneNumberOrToPhoneNumber("7036811375", "7036811375"))
				.thenReturn(lists);
		List<StatementsResponseDto> statements = statementsServiceImpl.getAllStatements("7036811375");
		assertEquals("7036811375", statements.get(0).getFromPhoneNumber());
		assertEquals(lists.size(), statements.size());
		assertNotNull(statements);
	}

	@Test
	public void testGetAllStatementsForAnyRegisteredNumber() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setToPhoneNumber("1234567890");
		fundTransfer.setFromPhoneNumber("7036811375");
		fundTransfer.setAmount(100);
		fundTransfer.setDate(LocalDateTime.now());
		fundTransfer.setDescription("sample");
		FundTransfer fundTransfer1 = new FundTransfer();
		fundTransfer1.setFromPhoneNumber("9652692085");
		fundTransfer1.setToPhoneNumber("7036811375");
		fundTransfer1.setAmount(10);
		fundTransfer1.setDate(LocalDateTime.now());
		fundTransfer1.setDescription("sample1");
		List<FundTransfer> lists = new ArrayList<>();
		lists.add(fundTransfer);
		lists.add(fundTransfer1);
		Mockito.when(fundTransferRepository.findByFromPhoneNumber(Mockito.anyString())).thenReturn(lists);
		List<StatementsResponseDto> statements = statementsServiceImpl.getAllStatements("1234567890");
		assertEquals("7036811375", statements.get(0).getFromPhoneNumber());

	}

	@Test
	public void testGetAllStatementsForException() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setToPhoneNumber("1234567890");
		fundTransfer.setFromPhoneNumber("7036811375");
		fundTransfer.setAmount(100);
		fundTransfer.setDate(LocalDateTime.now());
		fundTransfer.setDescription("sample");
		FundTransfer fundTransfer1 = new FundTransfer();
		fundTransfer1.setToPhoneNumber("9652692085");
		fundTransfer1.setFromPhoneNumber("7036811375");
		fundTransfer1.setAmount(10);
		fundTransfer1.setDate(LocalDateTime.now());
		fundTransfer1.setDescription("sample1");
		List<FundTransfer> lists = new ArrayList<>();
		lists.add(fundTransfer);
		lists.add(fundTransfer1);
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Direction.DESC, "date"));
		try {
			Mockito.when(
					fundTransferRepository.findByFromPhoneNumber("7036811375"))
					.thenReturn(lists);
			List<StatementsResponseDto> statements = statementsServiceImpl.getAllStatements("7036811376");

		} catch (StatementsNotFoundException exception) {
			System.out.println("statements not found");

		}

	}

}
