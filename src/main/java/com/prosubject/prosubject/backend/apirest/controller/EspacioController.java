package com.prosubject.prosubject.backend.apirest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.ForoService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;

@RestController
@RequestMapping("/api/espacios")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject.herokuapp.com"})
public class EspacioController{

	
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private ForoService foroService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private ProfesorService profesorService;
	
	
	@GetMapping("")
	public List<Espacio> findAll(){
		return this.espacioService.findAll();
	}
	
	@GetMapping("/espaciosDisponibles")
	public List<Espacio> findDisponibles(@RequestParam(value="universidad") String universidad, 
			@RequestParam(value="facultad") String facultad,
			@RequestParam(value="grado") String grado,
			@RequestParam(value="curso") String curso,
			@RequestParam(value="asignatura") String asignatura){
		return this.espacioService.findDisponibles(universidad, facultad, grado, curso, asignatura);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Espacio espacio = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			espacio = this.espacioService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(espacio == null) {
			response.put("mensaje",	 "El espacio con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Espacio>(espacio, HttpStatus.OK);
	}
	
	
	@PostMapping("")
	public Espacio crearEspacio(@RequestBody Espacio espacio ) {
		Espacio e = new Espacio();
		try {
			
			e = this.espacioService.save(espacio);
			
		}catch(Exception ex){
			this.foroService.delete(espacio.getForo());
		
		}
		
		return e;
		
	}
	
	
	
	@GetMapping("/espaciosProfesor/{id}")
	public ResponseEntity<?> espaciosDeUnProfesor(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Espacio> espacios = new ArrayList<>();
		Profesor profesor = this.profesorService.findOne(id);
			
		try {
			espacios = this.espacioService.espaciosDeUnProfesor(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(profesor == null) {
			response.put("mensaje",	 "El profesor con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<List<Espacio>>(espacios, HttpStatus.OK);	
	}
	//Jesus:metodo comentado debido a los cambios en el modelo
	@GetMapping("/espaciosAlumno/{id}")
	public ResponseEntity<?> espaciosDeUnAlumno(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Espacio> espacios = new ArrayList<>();
		Alumno alumno = this.alumnoService.findOne(id);
			
		try {
			espacios = this.espacioService.espaciosDeUnAlumno(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(alumno == null) {
			response.put("mensaje",	 "El alumno con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<List<Espacio>>(espacios, HttpStatus.OK);	
	}
	
	@GetMapping("/capacidad")
	public List<Espacio> espaciosConCapacidad(){
		return this.espacioService.espaciosConCapacidad();
	}
	
	


}
