package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JwtUser;
import com.example.demo.model.Login;
import com.example.demo.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	private JwtGenerator jwtGenerator;

	public TokenController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping
	public ResponseEntity<?> generate(@RequestBody final Login login) {
		JwtUser jwtUser = new JwtUser();
		// pedimos si el usuario existe
		jwtUser = existUser(login);
		if(jwtUser != null) {
			//convertir el token de texto plano a json
			//guardandolo como una lista
			List<String> lista = new ArrayList<>();
			lista.add(jwtGenerator.generate(jwtUser));
			return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	// creamos el usuario literalmente en tiempo de ejecución en lugar de acceder a
	// una base de datos
	private JwtUser existUser(Login login) {
		if (login.getUser().equals("usuario") && login.getPassword().equals("1234")) {
			JwtUser jwtUser = new JwtUser();
			jwtUser.setUserName(login.getUser());
			// id de la base de datos
			jwtUser.setId(1L);
			jwtUser.setRol("Admin");
			return jwtUser;
		} else {
			return null;
		}
	}

}
