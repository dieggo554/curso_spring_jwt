package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.demo.constants.Constants;
import com.example.demo.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationTokenFilter() {
		//lo que se cheque con JWT
		super("/api/**");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		//cogemos la cadena del header
		String header = request.getHeader(Constants.AUTHORIZATION_HEADER);
		
		if(header == null || !header.startsWith(Constants.BEARER_TOKEN)) {
			throw new RuntimeException("Jwt es incorrecto o no ha llegado");
		} else {
			String authenticationToken = header.substring(7);
			JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
			return getAuthenticationManager().authenticate(token);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}
