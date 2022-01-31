package com.sirisha.upi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirisha.upi.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	User findByEmail(String email);

	User findByPhoneNumber(String phoneNumber);

}
