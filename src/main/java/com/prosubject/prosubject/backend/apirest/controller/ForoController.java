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

import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.service.ForoService;

@RestController
@RequestMapping("/api/foros")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject.herokuapp.com"})
public class ForoController {

	@Autowired
	private ForoService foroService;
	
	@GetMapping("")
	public List<Foro> findAll(){
		return this.foroService.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id){
		Foro foro = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			foro = this.foroService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(foro == null) {
			response.put("mensaje",	 "El foro con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Foro>(foro, HttpStatus.OK);
		
	}
	
	@PostMapping("")
	public Foro crearEspacio(@RequestBody Foro foro ) {
		Foro f = new Foro();
		try {
			f = this.foroService.save(foro);
			
		}catch(Exception ex){
			
		}
		
		return f;
		
	}
	
}
