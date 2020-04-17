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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.AdministradorService;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class AdministradorController {

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private ProfesorService profesorService;

	@GetMapping("")
	public List<Administrador> findAll() {
		return this.administradorService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Administrador administrador = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			administrador = this.administradorService.findOne(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (administrador == null) {
			response.put("mensaje", "El administrador con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);

	}

	@PostMapping("")
	public ResponseEntity<?> crearAdministrador(@RequestBody Administrador administrador) {
		Map<String, Object> response = new HashMap<String, Object>();
		Administrador administradorNuevo = null;
		List<String> emails1 = this.administradorService.emailsAdministradores();
		List<String> dnis1 = this.administradorService.dnisAdministradores();
		List<String> users = this.userAccountService.todosUsername();
		dnis1.addAll(this.profesorService.DNIsProfesor());
		emails1.addAll(this.profesorService.emailsProfesor());
		dnis1.addAll(this.alumnoService.DNIsAlumno());
		emails1.addAll(this.alumnoService.emailsAlumno());

		if (dnis1.contains(administrador.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emails1.contains(administrador.getEmail())) {
			response.put("mensaje", "El email ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (users.contains(administrador.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				administradorNuevo = administradorService.save(administrador);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Administrador>(administradorNuevo, HttpStatus.CREATED);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarAdministrador(@RequestBody Administrador administrador, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Administrador administradorEditado = null;
		
		Administrador p = this.administradorService.findOne(id);

		List<String> emails1 = this.administradorService.emailsAdministradores();
		List<String> dnis1 = this.administradorService.dnisAdministradores();
		List<String> users = this.userAccountService.todosUsername();
		dnis1.addAll(this.profesorService.DNIsProfesor());
		emails1.addAll(this.profesorService.emailsProfesor());
		dnis1.addAll(this.alumnoService.DNIsAlumno());
		emails1.addAll(this.alumnoService.emailsAlumno());
		emails1.remove(p.getEmail());
		dnis1.remove(p.getDni());
		users.remove(p.getUserAccount().getUsername());

		if (dnis1.contains(administrador.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emails1.contains(administrador.getEmail())) {
			response.put("mensaje", "El email ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (users.contains(administrador.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				administradorEditado = administradorService.edit(id, administrador);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el edit en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Administrador>(administradorEditado, HttpStatus.OK);
		}
	}
	
	@GetMapping("/profesores")
	public ResponseEntity<?> profesoresOlvidados() {
		List<Profesor> profesores = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			profesores = this.administradorService.profesoresOlvidados();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/*

		if (profesores.isEmpty()) {
			response.put("mensaje", "No hay profesores ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		*/

		return new ResponseEntity<List<Profesor>>(profesores, HttpStatus.OK);

	}
	
	@GetMapping("/alumnos")
	public ResponseEntity<?> alumnosOlvidados() {
		List<Alumno> alumnos = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			alumnos = this.administradorService.alumnosOlvidados();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/*
		if (alumnos.isEmpty()) {
			response.put("mensaje", "No hay alumnos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		*/

		return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);

	}
	
	@DeleteMapping("/profesor/{profesorId}")
	public ResponseEntity<?> eliminarProfesor(@PathVariable Long profesorId ,  @RequestParam String username ) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor  profesor = this.profesorService.findOne(profesorId);
		
		if(profesor == null) {
			response.put("mensaje",	 "El profesor con ID: ".concat(profesorId.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		if(profesor.getDerechoOlvidado() == false) {
			response.put("mensaje",	 "El profesor no se puede borrar");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		
		Administrador administrador = this.administradorService.findByUsername(username);
		if( administrador == null) {
				response.put("mensaje",	 "El Username no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		try {
			this.administradorService.eliminarProfesor(profesor.getId());
		}catch(DataAccessException e) {
			response.put("mensaje", "Error en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El profesor ha sido borrado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
		
	}
	

	@DeleteMapping("/alumno/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long alumnoId ,  @RequestParam String username ) {
		Map<String, Object> response = new HashMap<String, Object>();
		Alumno  alumno = this.alumnoService.findOne(alumnoId);
		
		if(alumno == null) {
			response.put("mensaje",	 "El alumno con ID: ".concat(alumnoId.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		if(alumno.getDerechoOlvidado() == false) {
			response.put("mensaje",	 "El alumno no se puede borrar");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		
		Administrador administrador = this.administradorService.findByUsername(username);
		if( administrador == null) {
				response.put("mensaje",	 "El Username no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		try {
			this.administradorService.eliminarAlumno(alumno.getId());
		}catch(DataAccessException e) {
			response.put("mensaje", "Error en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El alumno ha sido borrado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
		
	}

	}


