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
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.model.Valoracion;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.ValoracionService;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class ValoracionController {
	
	@Autowired
	private ValoracionService valoracionService;
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private AlumnoService alumnoService ;
	
	@GetMapping("")
	public List<Valoracion> findAll() {
		return this.valoracionService.findAll();

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Valoracion valoracion = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			valoracion = this.valoracionService.findOne(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (valoracion == null) {
			response.put("mensaje", "La valoracion con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Valoracion>(valoracion, HttpStatus.OK);

	}
	
	@PostMapping("")
	public ResponseEntity<?> crearValoracion(@RequestBody Valoracion valoracion ) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		Valoracion valoracionGuardada = null;
		Espacio espacio = this.espacioService.findOne(valoracion.getEspacio().getId());
		List<Alumno> alumnosDelEspacio = this.alumnoService.alumnosDeUnEspacio(valoracion.getEspacio().getId());
		Alumno alumno = this.alumnoService.findOne(valoracion.getAlumno().getId());
		
		if (espacio==null) {
			response.put("mensaje", "El espacio que estas valorando no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if (alumno==null) {
			response.put("mensaje", "El alumno no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (!alumnosDelEspacio.contains(alumno) || alumnosDelEspacio.isEmpty()) {
			response.put("mensaje", "El alumno no pertenece al espacio");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		List<Alumno> alumnosQueHanValoradoUnespacio = this.alumnoService.alumnosQueHanValoradoUnEspacio(espacio.getId());
		if (alumnosQueHanValoradoUnespacio.contains(alumno)) {
			response.put("mensaje", "El alumno  ya ha valorado este espacio");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			valoracionGuardada  = valoracionService.save(valoracion);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<Valoracion>(valoracionGuardada,HttpStatus.CREATED); 	
		
	}
	
	@DeleteMapping("/{valoracionId}")
	public ResponseEntity<?> eliminarEspacio(@PathVariable Long valoracionId ,  @RequestParam String username ) {
		Map<String, Object> response = new HashMap<String, Object>();
		Valoracion  valoracion = this.valoracionService.findOne(valoracionId);
		
		if(valoracion == null) {
			response.put("mensaje",	 "La valoracion con ID: ".concat(valoracionId.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		
		Alumno alumno = this.alumnoService.findByUsername(username);
		if(alumno != null) {
			if(!alumno.equals(valoracion.getAlumno())) {
				response.put("mensaje",	 "El alumno no pertenece a la valoracion que quiere borrar");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
			}
		}else {
			response.put("mensaje",	 "El Username no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
			
		}
		
		try {
			this.valoracionService.delete(valoracion);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "La valoracion ha sido borrado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
		
	}
	
	
	@GetMapping("/espacio/{id}")
	public ResponseEntity<?> valoracionesPorEspacioId(@PathVariable Long id) {
		List<Valoracion> valoraciones = null;
		Map<String, Object> response = new HashMap<String, Object>();
		Espacio espacio = this.espacioService.findOne(id);
		if (espacio == null) {
			response.put("mensaje", "El  espacio con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			valoraciones = this.valoracionService.valoracionesPorEspacioId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (valoraciones.isEmpty()) {
			response.put("mensaje", "Este espacio no tiene valoraciones");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Valoracion>>(valoraciones, HttpStatus.OK);

	}
	
	
}
