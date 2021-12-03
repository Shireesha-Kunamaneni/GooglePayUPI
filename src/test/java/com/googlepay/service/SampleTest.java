package com.googlepay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SampleTest {
	
	@BeforeAll
	public static void setUp() {
		System.out.println("setup..");
	}
	
	
	@Test
	public void testSample() {
		assertEquals(2, 2);
	}

}
