package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface AccountService {
	List<User> finaAll();
	
	void register(String name, String email, String password, String[] roles);
}
