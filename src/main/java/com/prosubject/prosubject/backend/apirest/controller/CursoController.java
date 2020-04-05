package com.prosubject.prosubject.backend.apirest.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Curso;
import com.prosubject.prosubject.backend.apirest.service.CursoService;
import com.prosubject.prosubject.backend.apirest.service.GradoService;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject-v2.herokuapp.com"})
public class CursoController {

	
	@Autowired
	private CursoService cursoService;
	@Autowired
	private GradoService gradoService;
	
	
	@GetMapping("")
	public List<Curso> findAll(){
		return this.cursoService.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id){
		Curso curso = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			curso = this.cursoService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(curso == null) {
			response.put("mensaje",	 "El curso con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}
	
	@GetMapping("/grado")
	public ResponseEntity<?> findOne(@RequestParam(value="nombre") String nombre){
		List<Curso> cursos = null;
		Map<String, Object> response = new HashMap<String, Object>();
		Long gradoId = this.gradoService.findGradoId(nombre);
		
		if(gradoId == null) {
			response.put("mensaje",	 "No existe el nombre de ese grado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		try {
			cursos = this.cursoService.cursosPorGrado(nombre);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		
		return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
	}
	
	

}
