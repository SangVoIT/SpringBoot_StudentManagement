package com.example.demo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SimpleLoginUser;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SimpleUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public SimpleUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/**
	 * メールアドレスで検索したユーザーのUserエンティティをSimpleLoginUserクラスのインスタンスへ変換する
	 * 
	 * @param email 検索するユーザーのメールアドレス
	 * @return メールアドレスで検索できたユーザーのユーザー情報
	 * @throws UsernameNotFoundException メールアドレスユーザーが検索できなかった場合にスローする
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		assert(email != null);
		
		log.info("loadUserByUsername(email):[{}]", email);
		
		// Get User entity
		return userRepository.findByEmail(email)
				.map(SimpleLoginUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + email +"]"));
		}
	
}
