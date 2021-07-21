package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"email", "password"})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", length = 60, nullable = false)
	private String name;
	
	@Column(name = "email", length = 120, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 120, nullable = false)
	private String password;
	
	@Column(name = "roles", length = 120)
	private String roles;
	
	@Column(name = "enable_flg", nullable = false)
	private Boolean enable;

	public User(String name, String email, String password, String roles, Boolean enable) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enable = enable;
	}

}
