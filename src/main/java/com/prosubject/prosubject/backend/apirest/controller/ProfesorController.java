package com.prosubject.prosubject.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.model.ValidacionExpediente;
import com.prosubject.prosubject.backend.apirest.service.DBFileStorageService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private DBFileStorageService dBFileStorageService;

	@GetMapping("")
	public List<Profesor> findAll() {
		return this.profesorService.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Profesor profesor = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			profesor = this.profesorService.findOne(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (profesor == null) {
			response.put("mensaje", "El profesor con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);

	}
	// ----------------------------
	// ----------------------------

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarProfesor(@PathVariable Long id,@RequestParam("profesor") String profesor,@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
		Profesor prof=new ObjectMapper().readValue(profesor, Profesor.class);
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorEditado = null;

		try {
			
			DBFile dbFile = this.dBFileStorageService.storeFile(file);
			prof.setExpendiente(dbFile);
			profesorEditado = profesorService.edit(id, prof);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el edit en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Profesor>(profesorEditado, HttpStatus.OK);
	}

	// ----------------------------
	// ----------------------------

	@GetMapping("/aceptarExpediente/{id}")
	public ResponseEntity<?> aceptarExpediente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorModificado = null;

		Profesor prof = this.profesorService.findOne(id);
		Assert.assertNotNull(prof);

		try {
			prof.setExpedienteValidado(ValidacionExpediente.ACEPTADO);
			profesorModificado = this.profesorService.save(prof);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Profesor>(profesorModificado, HttpStatus.OK);
	}

	@GetMapping("/rechazarExpediente/{id}")
	public ResponseEntity<?> rechazarExpediente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorModificado = null;

		Profesor prof = this.profesorService.findOne(id);
		Assert.assertNotNull(prof);

		try {
			prof.setExpedienteValidado(ValidacionExpediente.RECHAZADO);
			profesorModificado = this.profesorService.save(prof);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Profesor>(profesorModificado, HttpStatus.OK);
	}

	@GetMapping("/validacionExpedientePendiente")
	public List<Profesor> profesoresExpedientePendiete() {
		return this.profesorService.profesoresExpedientePendiete();

	}

}
