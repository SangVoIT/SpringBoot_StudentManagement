package com.example.demo.entity;

import lombok.NoArgsConstructor;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
	@NotNull
	private String email;
	@NotNull
	private String name;
	@NotNull
	private String password;
}
