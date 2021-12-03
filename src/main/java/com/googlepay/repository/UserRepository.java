package com.googlepay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.googlepay.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByPhoneNumber(String fromPhoneNumber);

	User findByPhoneNumber(String phoneNumber);

}
