package com.prosubject.prosubject.backend.apirest.controller;

import java.util.HashMap;
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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject.herokuapp.com"})
public class SignUpController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private ProfesorService profesorService;
	
	
	@PostMapping("/signUpProfesor")
	public ResponseEntity<?> signUpProfesor(@RequestBody Profesor profesor ) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorNuevo = null;
		
		try {
			profesorNuevo = this.profesorService.save(profesor);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		return new ResponseEntity<Profesor>(profesorNuevo, HttpStatus.CREATED); 
	}
	
	
	@PostMapping("/signUpAlumno")
	public ResponseEntity<?> signUpAlumno(@RequestBody Alumno alumno ) {
		Map<String, Object> response = new HashMap<String, Object>();
		Alumno AlumnoNuevo = null;
		
		try {
			AlumnoNuevo = this.alumnoService.save(alumno);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		return new ResponseEntity<Alumno>(AlumnoNuevo, HttpStatus.CREATED); 
	}

}
