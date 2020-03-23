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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.ForoService;
import com.prosubject.prosubject.backend.apirest.service.HorarioService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;

@RestController
@RequestMapping("/api/foros")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject-v2.herokuapp.com"})
public class ForoController {

	@Autowired
	private ForoService foroService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private ProfesorService profesorService;
	@Autowired
	private HorarioService horarioService;
	@Autowired
	private EspacioService espacioService;
	
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
	public ResponseEntity<?> foroPorEspacioId(@PathVariable Long espacioId, @RequestParam String username, @RequestParam String autoridad){
		Foro foro = null;
		Espacio espacio = null;
		//List<Alumno> alumnos = null;
		//Profesor profesor = null;
		List<Horario> horarios = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		espacio = this.espacioService.findOne(espacioId);
		horarios = this.horarioService.horariosDeUnEspacio(espacioId);
	
		
		
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
		}else {
			
			if(autoridad.equals("ALUMNO")) {
				
				Alumno alumno = this.alumnoService.findByUsername(username);
				if(alumno != null) {		//HE CAMBIADO ESTA PARTE DEL CODIGO DEBIDO A QUE AHORA NO HAY UNA LISTA DE ALUMNOS EN HORARIO antes estaba .anyMatch(x->x.getAlumnos()).contains(alumno));
					Boolean res = horarios.stream().anyMatch(x->this.alumnoService.alumnosDeUnHorario(x.getId()).contains(alumno));
					if(!res) {
						response.put("mensaje",	 "El alumno no se encuentra inscrito en el espacio cuyo id es ".concat(espacioId.toString()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
					}
				}
			
			}else {
				
				Profesor profesor = this.profesorService.findByUsername(username);
				if(profesor != null) {
					if(!profesor.equals(espacio.getProfesor())) {
						response.put("mensaje",	 "El profesor no pertenece al espacio cuyo id es ".concat(espacioId.toString()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
					}
				}
				
			}	
		}
		return new ResponseEntity<Foro>(foro, HttpStatus.OK);
		
	}
	
}
