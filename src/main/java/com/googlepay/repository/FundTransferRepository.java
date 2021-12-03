package com.googlepay.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.googlepay.model.FundTransfer;

public interface FundTransferRepository extends JpaRepository<FundTransfer, Integer> {

	List<FundTransfer> findByFromPhoneNumber(String phoneNumber);

	List<FundTransfer> findByToPhoneNumber(String phoneNumber);

	List<FundTransfer> findByFromPhoneNumberOrToPhoneNumber(String phoneNumber,String tophoneNumber);

}
