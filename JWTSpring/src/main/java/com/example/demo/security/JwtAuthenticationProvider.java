package com.example.demo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.model.JwtAuthenticationToken;
import com.example.demo.model.JwtUser;
import com.example.demo.model.JwtUserDetails;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator validator;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
	
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String token = jwtAuthenticationToken.getToken();
		//validamos el token
		JwtUser jwtUser = validator.validate(token);
		
		System.out.println("authentication: " + authentication +
				"\njwtAuthenticationToken: " + jwtAuthenticationToken +
				"\ntoken: " + token +
				"\njwtUser: " + jwtUser);
		
		if (jwtUser == null) {
			throw new RuntimeException("JWT es incorrecto");
		}
		
		List<GrantedAuthority>  grantedAuthorities = AuthorityUtils
				//en estos casos será solo un rol por usuario
				.commaSeparatedStringToAuthorityList(jwtUser.getRol());
		
		return new JwtUserDetails(jwtUser.getUserName(), token, jwtUser.getId(), grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
