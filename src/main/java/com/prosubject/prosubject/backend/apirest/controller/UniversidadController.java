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
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Universidad;
import com.prosubject.prosubject.backend.apirest.service.UniversidadService;


@RestController
@RequestMapping("/api/universidades")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject.herokuapp.com"})
public class UniversidadController {

	
	@Autowired
	private UniversidadService universidadService;
	
	@GetMapping("")
	public List<Universidad> findAll(){
		return this.universidadService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id){
		Universidad uni = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			uni = this.universidadService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(uni == null) {
			response.put("mensaje",	 "La universidad con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Universidad>(uni, HttpStatus.OK);
	}
}
