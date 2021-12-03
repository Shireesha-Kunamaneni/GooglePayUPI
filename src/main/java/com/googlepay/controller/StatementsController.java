package com.googlepay.controller;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlepay.dto.StatementsResponseDto;
import com.googlepay.service.StatementsService;

@RestController
@RequestMapping("/statements")
@Validated
public class StatementsController {
	
	@Autowired
	private StatementsService statementsService;
	
	@GetMapping("/allStatementsUsingPagination/{phoneNumber}")
	public List<StatementsResponseDto> getAllStatements(@PathVariable @NotNull(message = "phone number should not be empty")
	@Size(min = 10, max = 10, message = "phone number should be 10 numbers")
	@Pattern(regexp = "^[0-9]{10}$", message = "phone number must contain numbers only") String phoneNumber){
		return statementsService.getAllStatements(phoneNumber);
		
	}

}
