package com.example.demo.security;

import org.springframework.stereotype.Component;

import com.example.demo.constants.Constants;
import com.example.demo.model.JwtUser;

import ch.qos.logback.classic.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		
		try {
			Claims body = Jwts.parser()
					.setSigningKey(Constants.YOUR_SECRET)
					//parseClaimsJws != parseClaimsJwt
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			jwtUser.setId(Long.parseLong((String) body.get(Constants.USER_ID)));
			jwtUser.setRol((String) body.get(Constants.ROLE));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		
		return jwtUser;
	}
	
}
