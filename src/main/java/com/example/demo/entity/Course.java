package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "course")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "title", length = 60, nullable = false)
	public String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "duration")
	public long duration;
	
	@Column(name = "cost")
	public long cost;

	@Column(nullable = true, length = 64)
	private String photo;
	
	@Column(name = "create_user")
	public int createUser;
	
	@Column(name = "create_date")
	public Date createDate;
	
	@Column(name = "update_user")
	public int updateUser;
	
	@Column(name = "update_date")
	public Date updateDate;
	
	@Column(name = "delete_flg")
	public Date deleteFlg;
}
