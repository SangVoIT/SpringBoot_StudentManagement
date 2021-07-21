package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.auth.MySimpleUrlAuthenticationSuccessHandler;
import com.example.demo.auth.SimpleAccessDeniedHandler;
import com.example.demo.auth.SimpleAuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSercurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${logging.file.name}")
	private String logPath;
	
	// アカウント登録時のパスワードエンコードで利用するためDI管理する
	@Bean
	PasswordEncoder passwordEncoder() {
		// Dependency Injection: Password Encoding
		log.debug("WebSercurityConfig: PasswordEncoder @Bean");
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		log.debug("WebSercurityConfig: AuthenticationSuccessHandler @Bean");
		return new MySimpleUrlAuthenticationSuccessHandler();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println("logPath" + logPath);
		log.debug("WebSercurityConfig: configure(WebSecurity web) @Override");
		web.debug(false)
		.ignoring()
		.antMatchers("/images/**", "/js/**", "/css/**", "/user-photos/**");
		// Static resource load 
		// 認証が行われないように設定する
		// /resource/images/...; /resource/jss/...; /resource/css/...;
	}
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("WebSercurityConfig: configure(HttpSecurity http) @Override");
		http
			.authorizeRequests()
				// "/", "/signup": is permit for all request 
		      	// トップページとアカウント登録処理はだれでもアクセスできる
				.antMatchers("/home/**", "/signup").permitAll()
				// .antMatchers("/", "/signup", "/students/**").permitAll()
				// .antMatchers("/**").permitAll()
				
				// all request /members/user/** has USER role
			    // /members/user/**ページは、USERロールを持つ認証ユーザーがアクセスできる
				.mvcMatchers("/members/user/**").hasRole("USER")
				
				// all request /members/admin/** has ADMIN role
			    // /members/admin/**ページは、ADMINロールを持つ認証ユーザーがアクセスできる
				.mvcMatchers("/members/admin/**").hasRole("ADMIN")
				
				// Any other request had to authenticated
			    // 上記以外のページは、認証ユーザーがアクセスできる
				.anyRequest().authenticated()

            .and()
            // EXCEPTION
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                
			.and()
			// Login form setting
			.formLogin()
				// In the below code, passed parameter will be pass to UserDetailsService class, and authenticate account
				.loginPage("/login")				// Customize Login Entry from Spring Security
				.loginProcessingUrl("/login")		// Action Form
					.usernameParameter("username")  // Username Parameter
					.passwordParameter("password")  // Password Parameter
				
				// #defaultSuccessUrl: The default success url
				// alwaysUse: true if the defaultSuccessUrl should be used after authentication despite if a protected page had be visited
				// .defaultSuccessUrl("/", true)
				// .defaultSuccessUrl("/home", true)
				.successHandler(myAuthenticationSuccessHandler())

				.failureForwardUrl("/login?error")
				.permitAll()
				// Default Login form (Spring Security Support) 
				// .defaultSuccessUrl("/")
			.and()
			// Login setting success 
			.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/");
	}
	
	private AccessDeniedHandler accessDeniedHandler() {
		return new SimpleAccessDeniedHandler();
	}

	AuthenticationEntryPoint authenticationEntryPoint() {
		return new SimpleAuthenticationEntryPoint();
		
	}
	
}
