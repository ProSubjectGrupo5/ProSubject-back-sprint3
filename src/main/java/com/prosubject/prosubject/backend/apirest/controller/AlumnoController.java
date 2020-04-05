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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private EspacioService espacioService;

	@Autowired
	private UserAccountService userAccountService;

	@GetMapping("")
	public List<Alumno> findAll() {
		return this.alumnoService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Alumno alumno = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			alumno = this.alumnoService.findOne(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (alumno == null) {
			response.put("mensaje", "El alumno con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);

	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Alumno alumnoEditado = null;

		Alumno p = this.alumnoService.findOne(id);

		List<String> emails = this.alumnoService.emailsAlumno();
		List<String> dnis = this.alumnoService.DNIsAlumno();
		List<String> users = this.userAccountService.todosUsername();

		emails.remove(p.getEmail());
		dnis.remove(p.getDni());
		users.remove(p.getUserAccount().getUsername());

		if (dnis.contains(alumno.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso por un alumno");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emails.contains(alumno.getEmail())) {
			response.put("mensaje", "El email ya esta en uso por un alumno");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (users.contains(alumno.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				alumnoEditado = alumnoService.edit(id, alumno);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el edit en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Alumno>(alumnoEditado, HttpStatus.OK);
		}
	}

	@GetMapping("/horario/{id}")
	public ResponseEntity<?> alumnosDeUnHorario(@PathVariable Long id) {
		List<Alumno> alumno = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			alumno = this.alumnoService.alumnosDeUnHorario(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (alumno == null) {
			response.put("mensaje", "El alumno con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Alumno>>(alumno, HttpStatus.OK);

	}
	
	@GetMapping("/espacio/{id}")
	public ResponseEntity<?> alumnosDeUnEspacio(@PathVariable Long id) {
		List<Alumno> alumno = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		Espacio espacio = this.espacioService.findOne(id);
		
		if (espacio == null) {
			response.put("mensaje", "El espacio con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			alumno = this.alumnoService.alumnosDeUnEspacio(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (alumno.isEmpty()) {
			response.put("mensaje", "No hay alumnos en ese espacio");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Alumno>>(alumno, HttpStatus.OK);

	}

}
