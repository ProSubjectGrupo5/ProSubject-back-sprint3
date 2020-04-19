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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Carrito;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.CarritoService;
import com.prosubject.prosubject.backend.apirest.service.DBFileStorageService;
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

	@Autowired
	private DBFileStorageService dBFileStorageService;

	@Autowired
	private CarritoService carritoService;

	@PostMapping("/signUpProfesor")
	public ResponseEntity<?> signUpProfesor(@RequestParam("profesor") String profesor,
			@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
		Profesor prof = new ObjectMapper().readValue(profesor, Profesor.class);
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorNuevo = null;
		List<String> emails = this.profesorService.emailsProfesor();
		List<String> dnis = this.profesorService.DNIsProfesor();
		List<String> users = this.userAccountService.todosUsername();

		if (dnis.contains(prof.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso por un profesor");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emails.contains(prof.getEmail())) {
			response.put("mensaje", "El email ya esta en uso por un profesor");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (users.contains(prof.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
			response.put("mensaje", "El expediente no esta en formato pdf");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if(file.getSize()>1000000) {
    		response.put("mensaje",	 "El archivo que intenta subir es demasiado grande");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
    	}else {

			try {

				DBFile dbFile = this.dBFileStorageService.storeFile(file);

				prof.setExpendiente(dbFile);
				prof.setDerechoOlvidado(false);
				profesorNuevo = this.profesorService.save(prof);

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

				alumno.setContadorDescuento(0);

				alumno.setDerechoOlvidado(false);

				AlumnoNuevo = this.alumnoService.save(alumno);

				Carrito car = new Carrito();

				car.setAlumno(AlumnoNuevo);
				car.setPrecioMensual(0.0);

				this.carritoService.save(car);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Alumno>(AlumnoNuevo, HttpStatus.CREATED);
		}
	}

}
