package com.googlepay.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "http://BANK-SERVICE/bank/users")
public interface BankClient {

	@GetMapping("/{phoneNumber}/user")
	Boolean getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber);

}
