package com.prosubject.prosubject.backend.apirest.controller;

import java.util.Collection;
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
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.HorarioService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject-v2.herokuapp.com"})
public class HorarioController{
	
	@Autowired
	private HorarioService horarioService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private EspacioService espaciosService;
	@Autowired
	private ProfesorService profesorService;	

	

	@GetMapping("")
	public List<Horario> findAll(){
		return this.horarioService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Horario horario = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			horario = this.horarioService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(horario == null) {
			response.put("mensaje",	 "El horario con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Horario>(horario, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> crearHorarios(@RequestBody Collection<Horario> horario ) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Horario> horariosGuardados = null;
		try {
			horariosGuardados = horarioService.save(horario);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<List<Horario>>(horariosGuardados,HttpStatus.CREATED); 
		
		
		
	}
	
	@PostMapping("/crearUnHorario")
	public ResponseEntity<?> crearHorario(@RequestBody Horario horario ) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		Horario horarioGuardado = null;
		try {
			horarioGuardado  = horarioService.saveOne(horario);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<Horario>(horarioGuardado,HttpStatus.CREATED); 
		
		
		
	}
	
	@PutMapping("/modificarUnHorario")
	public ResponseEntity<?> modificarHorario(@RequestBody Horario horario ) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		Horario horarioGuardado = null;
		try {
			horarioGuardado =horarioService.saveOne(horario);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
				
		}catch(Exception e) {
			response.put("mensaje", " Ha ocurrido un error:");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
				
			
		}
		
		return new ResponseEntity<Horario>(horarioGuardado,HttpStatus.CREATED); 
		
		
		
	}
//	
//	
//	//Modificar collection de horarios , si en un futuro no se utiliza borrar
//	@PutMapping("")
//	public ResponseEntity<?> modificarHorario(@RequestBody Collection<Horario> horario ) throws Exception {
//		Map<String, Object> response = new HashMap<String, Object>();
//		List<Horario> horariosGuardados = null;
//		try {
//			horariosGuardados=horarioService.save(horario);
//		}catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
//				
//		}catch(Exception e) {
//			response.put("mensaje", " Ha ocurrido un error:");
//			response.put("error", e.getMessage());
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
//				
//			
//		}
//		
//		return new ResponseEntity<List<Horario>>(horariosGuardados,HttpStatus.CREATED); 
//		
//		
//		
//	}
//	
//
//	
	@GetMapping("espacio/{id}")
	public ResponseEntity<?> horariosDeUnEspacio(@PathVariable Long id , @RequestParam String username) {
		List<Horario> horarios = null;
		Map<String, Object> response = new HashMap<String, Object>();
		Espacio espacio = this.espaciosService.findOne(id);
		
		try {
			horarios = this.horarioService.horariosDeUnEspacio(id);
		}catch(DataAccessException e ) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(espacio == null) {
			response.put("mensaje",	 "El espacio con ID: ".concat(id.toString()).concat(" no esxite"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		if(espacio.getDraftMode() == 0) {
			response.put("mensaje",	 "El espacio con ID: ".concat(id.toString()).concat(" no se encuentra entre tus espacios editables"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}else {
			
			Profesor profesor = this.profesorService.findByUsername(username);
			if(profesor != null) {
				if(!profesor.equals(espacio.getProfesor())) {
					response.put("mensaje",	 "El profesor no pertenece al espacio cuyo id es ".concat(id.toString()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
				}
			}
			
		}	
		
		
		
		
		
		return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
	}
	
	
	@PutMapping("/insertarAlumno")
	public ResponseEntity<?> insertarAlumno(@RequestParam Long horarioId, @RequestParam Long alumnoId) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		Horario horarioModificado = null;
		
		Horario horario = this.horarioService.findOne(horarioId);
		Alumno alumno = this.alumnoService.findOne(alumnoId);
		List<Alumno> alumnos = this.alumnoService.alumnosDeUnHorario(horarioId);
		
		if(horario == null) {
			response.put("mensaje",	 "El horario con ID: ".concat(horarioId.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		if(alumno == null) {
			response.put("mensaje",	 "El alumno con ID: ".concat(alumnoId.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		
		if(alumnos.contains(alumno)) {
			response.put("mensaje",	 "El alumno ya se encuentra inscrito en el horario.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		if(horario.getCapacidad().equals((long)alumnos.size())) {
			response.put("mensaje",	 "El horario ya tiene aforo completo.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		try {
			horarioModificado = this.horarioService.añadirAlumno(horarioId, alumnoId);	
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<Horario>(horarioModificado, HttpStatus.OK);	
	}
	
//	
	
	@GetMapping("/alumno/{alumnoId}")
	public ResponseEntity<?> horariosDeAlumno(@PathVariable Long alumnoId) throws Exception {
		List<Horario> horarios = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
		horarios = this.horarioService.horariosDeAlumno(alumnoId);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(horarios.isEmpty()) {
			response.put("mensaje",	 "El alumno con ID: ".concat(alumnoId.toString()).concat(" no tiene horarios en ese espacio"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
	}
	
	
	//CRISTIAN
	@GetMapping("/profesor/{profesorId}")
	public ResponseEntity<?> horariosDeProfesor(@PathVariable Long profesorId) throws Exception {
		List<Horario> horarios = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
		horarios = this.horarioService.horariosDeProfesor(profesorId);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(horarios.isEmpty()) {
			response.put("mensaje",	 "El profesor con ID: ".concat(profesorId.toString()).concat(" no tiene horarios en ese espacio"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
	}
	
	@GetMapping("espaciosNoEditables/profesor/{profesorId}")
	public ResponseEntity<?> horariosNoEditablesDeUnProfesor(@PathVariable Long profesorId) throws Exception {
		List<Horario> horarios = null;
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesor = this.profesorService.findOne(profesorId);
		
		try {
			horarios = this.horarioService.horariosNoEditablesDeUnProfesor(profesorId);
		}catch(DataAccessException e ) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(profesor == null) {
			response.put("mensaje",	 "El profesor con ID: ".concat(profesorId.toString()).concat(" no esxite"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		if(horarios.isEmpty()) {
			response.put("mensaje",	 "El profesor con ID: ".concat(profesorId.toString()).concat(" no tiene ningun horario con espacios no disponibles"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		
		
		
		
		return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
	}
	
	




}
