package com.sirisha.upi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sirisha.upi.model.UserFundTransfer;

public interface UserFundTransferRepository extends JpaRepository<UserFundTransfer, Integer> {

	
	/*
	 * @Query(value="select * from fundtransfer_user where date like :date% "
	 * ,nativeQuery = true) List<UserFundTransfer> findByMonthAndYear(@Param("date")
	 * LocalDateTime date);
	 */
	
	@Query(value="select *from fundtransfer WHERE YEAR(DATE(date))=:year AND MONTH(DATE(date)) =:month",nativeQuery=true)
	List<UserFundTransfer> findByDateContains(int year,int month);

	
}
