package com.prosubject.prosubject.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	//@Autowired
	//private AnswerService answerService;
	
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
	public ResponseEntity<?> crearForo(@RequestBody Foro foro) {
		Map<String, Object> response = new HashMap<String, Object>();
		Foro foroNuevo = null;
		//Respuesta respuesta = null;
		
		try {
			
			foroNuevo = foroService.save(foro);
			//respuesta = answerService.save(respuesta)
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		return new ResponseEntity<Foro>(foroNuevo, HttpStatus.CREATED); 
	}
	
	@DeleteMapping(value = "/foros/{id}")
    public void deleteForo(@RequestBody Foro foro,@PathVariable Long id) {

       this.foroService.delete(foro);
	
	}
	
	@GetMapping("/espacio/{espacioId}")
	public ResponseEntity<?> foroPorEspacioId(@PathVariable Long espacioId){
		Foro foro = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			foro = this.foroService.foroPorEspacioId(espacioId);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(foro == null) {
			response.put("mensaje",	 "No se ha encontrado ningun foro con espacioId: ".concat(espacioId.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Foro>(foro, HttpStatus.OK);
		
	}
	
}
