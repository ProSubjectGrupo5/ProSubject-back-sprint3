package com.prosubject.prosubject.backend.apirest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Authority;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.model.UserAccount;
import com.prosubject.prosubject.backend.apirest.service.AdministradorService;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;


@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject.herokuapp.com"})
public class LoginController {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private AdministradorService administradorService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private ProfesorService profesorService;

	@GetMapping("")
	public ResponseEntity<?> logearse(@RequestParam String username, @RequestParam String password) {
		Administrador administrador = null;
		Alumno alumno = null;
		Profesor profesor = null;
		UserAccount userAccount = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		
		try {
			userAccount = this.userAccountService.findByUserAndPass(username, password);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(userAccount == null) {
			response.put("mensaje",	 "El usuario no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}else if (userAccount.getAutoridad() == Authority.ADMIN) {
			administrador = this.administradorService.findByUserAccount(userAccount.getId());
			return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);
		} else if (userAccount.getAutoridad() == Authority.PROFESOR) {
			profesor = this.profesorService.findByUserAccount(userAccount.getId());
			return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
		} else {
			alumno = this.alumnoService.findByUserAccount(userAccount.getId());
			return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		}

	}

}
