package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> finaAll() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public void register(String name, String email, String password, String[] roles) {
		// Check existing account in DB
		if (userRepository.findByEmail(email).isPresent()) {
			throw new RuntimeException("Invalid name or email");
		}
		
		// Encode Password, make Roles String
		String encodedPassword = passwordEncode(password);
		String joinedRoles = joinRoles(roles);
		
		// Insert New Account to DB
		log.debug("Account_name:{}, email:{}, roles:{}", name, email, joinedRoles);
		User user = new User(name, email, encodedPassword, joinedRoles, Boolean.TRUE);
		userRepository.saveAndFlush(user);
	}

	
	private String passwordEncode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	private String joinRoles(String[] roles) {
		if (roles == null || roles.length == 0) {
			return "";
		}
		
		return Stream.of(roles)
				.map(String::trim)
				.map(String::toUpperCase)
				.collect(Collectors.joining(","));
	}
}
