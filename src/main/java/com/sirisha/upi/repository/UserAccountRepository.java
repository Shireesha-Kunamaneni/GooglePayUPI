package com.sirisha.upi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sirisha.upi.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	UserAccount findByUser_userId(int userId);

	UserAccount findByAccountNumber(long fromAccountNumber);

}
