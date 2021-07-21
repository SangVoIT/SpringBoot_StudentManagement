package com.example.demo.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.debug("MySimpleUrlAuthenticationSuccessHandler: onAuthenticationSuccess()");
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
		
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		log.debug("MySimpleUrlAuthenticationSuccessHandler: clearAuthenticationAttributes()");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		log.debug("MySimpleUrlAuthenticationSuccessHandler: handle()");
		String targetUrl = determineTargetUrl(authentication);
		
		if (response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to "
                    + targetUrl);
			return;

		}
		
		redirectStategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(final Authentication authentication) {
		log.debug("MySimpleUrlAuthenticationSuccessHandler: determineTargetUrl()");

		
		Map<String,String> roleTargetUrlMap = new HashMap<>();
		
		roleTargetUrlMap.put("ROLE_USER", "/members/user");
		roleTargetUrlMap.put("ROLE_ADMIN", "/members/admin");
		
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		for (final GrantedAuthority grantedAuthority: authorities) {
			String authorityName = grantedAuthority.getAuthority();
			if (roleTargetUrlMap.containsKey(authorityName)) {
				log.debug("MySimpleUrlAuthenticationSuccessHandler: authorityName: " + authorityName);
				return roleTargetUrlMap.get(authorityName);
			}
			
		}

		log.debug("MySimpleUrlAuthenticationSuccessHandler: IllegalStateException()");
		throw new IllegalStateException();
	}
	
	
}