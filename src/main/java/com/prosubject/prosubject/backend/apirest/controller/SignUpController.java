package com.prosubject.prosubject.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class SignUpController {

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private ProfesorService profesorService;

	@Autowired
	private UserAccountService userAccountService;

	@PostMapping("/signUpProfesor")
	public ResponseEntity<?> signUpProfesor(@RequestBody Profesor profesor) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorNuevo = null;
		List<String> emails = this.profesorService.emailsProfesor();
		List<String> dnis = this.profesorService.DNIsProfesor();
		List<String> users = this.userAccountService.todosUsername();

		if (dnis.contains(profesor.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso por un profesor");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emails.contains(profesor.getEmail())) {
			response.put("mensaje", "El email ya esta en uso por un profesor");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (users.contains(profesor.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				profesorNuevo = this.profesorService.save(profesor);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Profesor>(profesorNuevo, HttpStatus.CREATED);
		}
	}

	@PostMapping("/signUpAlumno")
	public ResponseEntity<?> signUpAlumno(@RequestBody Alumno alumno) {
		Map<String, Object> response = new HashMap<String, Object>();
		Alumno AlumnoNuevo = null;
		List<String> emails = this.alumnoService.emailsAlumno();
		List<String> dnis = this.alumnoService.DNIsAlumno();
		List<String> users = this.userAccountService.todosUsername();

		if (dnis.contains(alumno.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso por un alumno");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emails.contains(alumno.getEmail())) {
			response.put("mensaje", "El email ya esta en uso por un alumno");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (users.contains(alumno.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				AlumnoNuevo = this.alumnoService.save(alumno);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Alumno>(AlumnoNuevo, HttpStatus.CREATED);
		}
	}

}
