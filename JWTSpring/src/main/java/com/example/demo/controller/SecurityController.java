package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {

	@GetMapping("/acceso_solo_con_jwt")
	public ResponseEntity<?> getInformacionBancaria(){
		List<String> movimientosBancarios = obtenerUltimosMovimientosBancarios();
		if(movimientosBancarios != null) {
			return new ResponseEntity<>(movimientosBancarios, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	//lo hacemos para acortar directamente sin acceder a bd
	private List<String> obtenerUltimosMovimientosBancarios() {
		List<String> movimientosBancarios = new ArrayList<String>();
		movimientosBancarios.add("20€");
		movimientosBancarios.add("-20€");
		movimientosBancarios.add("400€");
		movimientosBancarios.add("-520€");
		return movimientosBancarios;
	}
}
