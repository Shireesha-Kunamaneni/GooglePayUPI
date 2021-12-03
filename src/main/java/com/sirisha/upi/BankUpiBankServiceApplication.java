package com.sirisha.upi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class BankUpiBankServiceApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(BankUpiBankServiceApplication.class, args);
	}
	

}
