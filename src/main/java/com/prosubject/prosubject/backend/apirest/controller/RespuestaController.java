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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prosubject.prosubject.backend.apirest.model.Respuesta;

import com.prosubject.prosubject.backend.apirest.service.RespuestaService;

@RestController
@RequestMapping("/api/respuestas")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject-v3.herokuapp.com"})
public class RespuestaController{
	
	@Autowired
	private RespuestaService respuestaService;
	
	@GetMapping("")
	public List<Respuesta> findAll(){
		return this.respuestaService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Respuesta respuesta = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			respuesta = this.respuestaService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(respuesta == null) {
			response.put("mensaje",	 "La respuesta con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		
	}

	@PostMapping("")
	public ResponseEntity<?> crearRespuesta(@RequestBody Respuesta respuesta) {
		Map<String, Object> response = new HashMap<String, Object>();
		Respuesta respuestaNueva = null;
		
		try {
			respuestaNueva = this.respuestaService.save(respuesta);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		return new ResponseEntity<Respuesta>(respuestaNueva, HttpStatus.CREATED); 
	}


	@GetMapping("/foro/{foroId}")
	public ResponseEntity<?> respuestaPorForoId(@PathVariable Long foroId){
		List<Respuesta> respuesta = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			respuesta = this.respuestaService.respuestaPorForoId(foroId);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(respuesta == null) {
			response.put("mensaje",	 "No se ha encontrado ninguna respuesta con foroId: ".concat(foroId.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<List<Respuesta>>(respuesta, HttpStatus.OK);
		
	}


}
