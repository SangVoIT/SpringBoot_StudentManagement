package com.example.demo.entity;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Slf4j
public class SimpleLoginUser extends org.springframework.security.core.userdetails.User{

	// DBより検索したUSERエンティティ
	// アプリケーションから利用されるのでフィールドに定義
	private User user;
	
	public User getUser() {
		log.debug("SimpleLoginUser getUser");
		return user;
	}

	/***
	 * DBより検索したUserエンティティよりSpring Securityで使用するユーザー認証情報のインスタンスを生成する
	 * @param user
	 */
	public SimpleLoginUser(User user) {
		super(user.getEmail(), user.getPassword(), user.getEnable(), true, true,
				true, convertGrantedAuthorities(user.getRoles()));
		log.debug("SimpleLoginUser Constructor");

		this.user = user;
	}
	
	
	/**
	 * カンマ区切りのロールをSimpleGrantedAuthorityのコレクションへ変換する
	 * @param roles カンマ区切りのロール
	 * @return SimpleGrantedAuthorityのコレクション
	 */
	static Set<GrantedAuthority> convertGrantedAuthorities(String roles) {
		log.debug("convertGrantedAuthorities");
		
		if (roles == null || roles.isEmpty()) {
			return Collections.emptySet();
		}
			
		Set<GrantedAuthority> authorities = Stream.of(roles.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toSet());
					
		return authorities;
	}



}
