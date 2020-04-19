package com.prosubject.prosubject.backend.apirest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.model.ValidacionExpediente;
import com.prosubject.prosubject.backend.apirest.service.DBFileStorageService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private DBFileStorageService dBFileStorageService;

	@GetMapping("")
	public List<Profesor> findAll() {
		return this.profesorService.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Profesor profesor = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			profesor = this.profesorService.findOne(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (profesor == null) {
			response.put("mensaje", "El profesor con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);

	}
	// ----------------------------
	// ----------------------------

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarProfesor(@PathVariable Long id, @RequestParam("profesor") String profesor,
			@RequestParam(required = false, name = "file") MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		Profesor prof = new ObjectMapper().readValue(profesor, Profesor.class);
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorEditado = null;

		Profesor p = this.profesorService.findOne(id);

		List<String> emailsEnuso = this.profesorService.emailsProfesor();
		emailsEnuso.remove(p.getEmail());
		List<String> dnisEnuso = this.profesorService.DNIsProfesor();
		dnisEnuso.remove(p.getDni());
		List<String> usersEnuso = this.userAccountService.todosUsername();
		usersEnuso.remove(p.getUserAccount().getUsername());

		if (dnisEnuso.contains(prof.getDni())) {
			response.put("mensaje", "El DNI ya esta en uso por un profesor");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (emailsEnuso.contains(prof.getEmail())) {
			response.put("mensaje", "El email ya se encuentra en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (usersEnuso.contains(prof.getUserAccount().getUsername())) {
			response.put("mensaje", "El nombre de usuario ya esta en uso");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				if(file.getSize()>1000000) {
		    		response.put("mensaje",	 "El archivo que intenta subir es demasiado grande");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		    	}
				if (file != null) {
					if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
						response.put("mensaje", "El expediente no tiene formato pdf");
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

					} else {
						if (p.getExpendiente() != null) {
							DBFile borrarPdf = this.profesorService.findOne(id).getExpendiente();
							this.dBFileStorageService.delete(borrarPdf);
						}
						DBFile dbFile = this.dBFileStorageService.storeFile(file);
						prof.setExpendiente(dbFile);
					}
				}

				profesorEditado = profesorService.edit(id, prof);
			} catch (DataAccessException e) {
				response.put("mensaje", "Se ha producido un error");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Profesor>(profesorEditado, HttpStatus.OK);
		}
	}

	// ----------------------------
	// ----------------------------

	@GetMapping("/aceptarExpediente/{id}")
	public ResponseEntity<?> aceptarExpediente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorModificado = null;

		Profesor prof = this.profesorService.findOne(id);
		Assert.assertNotNull(prof);

		try {
			prof.setExpedienteValidado(ValidacionExpediente.ACEPTADO);
			profesorModificado = this.profesorService.save(prof);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Profesor>(profesorModificado, HttpStatus.OK);
	}

	@GetMapping("/rechazarExpediente/{id}")
	public ResponseEntity<?> rechazarExpediente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Profesor profesorModificado = null;

		Profesor prof = this.profesorService.findOne(id);
		Assert.assertNotNull(prof);

		try {
			prof.setExpedienteValidado(ValidacionExpediente.RECHAZADO);
			profesorModificado = this.profesorService.save(prof);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Profesor>(profesorModificado, HttpStatus.OK);
	}

	//----------Tarifa Premium----------//
	
		@GetMapping("/pagoTarifaPremium/{id}")
		public ResponseEntity<?> inscribirPremium(@PathVariable Long id) {
			Map<String, Object> response = new HashMap<String, Object>();
			Profesor profesorModificado = null;
			Profesor prof = this.profesorService.findOne(id);
			Assert.assertNotNull(prof);
			
		if(prof.getTarifaPremium()==Boolean.TRUE) {
			//Comprueba si ya lleva 30 dias con el premium
			if(numeroDiasEntreDosFechas(prof.getFechaPagoPremium(),new Date())<30) {
				Integer diasPremium = 30 - (numeroDiasEntreDosFechas(prof.getFechaPagoPremium(),new Date()));
				response.put("mensaje", "El profesor aun posee "+String.valueOf(diasPremium)+"dias de plan premium");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			else if(numeroDiasEntreDosFechas(prof.getFechaPagoPremium(),new Date())==30){
				response.put("mensaje", "El plan premium del profesor acaba hoy");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			else {
			try {
				prof.setTarifaPremium(Boolean.TRUE);
				prof.setFechaPagoPremium(new Date());
				profesorModificado = this.profesorService.save(prof);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el update en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			}
		return new ResponseEntity<Profesor>(profesorModificado, HttpStatus.OK);
		}
		
		
		
		//------Comprueba cuantos dias de premium le quedan al profesor------//
		
		@GetMapping("/comprobarDiasPremium/{id}")
		public ResponseEntity<?> diasPremium(@PathVariable Long id) {
			Map<String, Object> response = new HashMap<String, Object>();
			Profesor prof = this.profesorService.findOne(id);
			Assert.assertNotNull(prof);
			
			if(prof.getTarifaPremium()==Boolean.TRUE) {
				//Comprueba si ya lleva 30 dias con el premium
				if(numeroDiasEntreDosFechas(prof.getFechaPagoPremium(),new Date())<30) {
					Integer diasPremium = 30 - (numeroDiasEntreDosFechas(prof.getFechaPagoPremium(),new Date()));
					response.put("mensaje", "El profesor aun posee "+String.valueOf(diasPremium)+"dias de plan premium");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				else if(numeroDiasEntreDosFechas(prof.getFechaPagoPremium(),new Date())==30){
					response.put("mensaje", "El plan premium del profesor acaba hoy");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			/*else {
			try {
				
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	*/
			return new ResponseEntity<Profesor>(prof, HttpStatus.OK);
		}

		//--------------------------//
		
		
		@GetMapping("/validacionExpedientePendiente")
		public List<Profesor> profesoresExpedientePendiete() {
			return this.profesorService.profesoresExpedientePendiete();

		}
		
		@GetMapping("/profesoresTarifaPremium")
		public List<Profesor> profesoresTarifaPremium() {
			return this.profesorService.profesoresExpedientePendiete();
		}
		
		
		///-------Auxiliar
		public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
		     long startTime = fecha1.getTime();
		     long endTime = fecha2.getTime();
		     long diffTime = endTime - startTime;
		     return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
		}
		
		
		
		@PutMapping("/peticionBorrar/{profesorId}")
		public ResponseEntity<?> modificarHorario(@PathVariable Long profesorId) throws Exception {
			Map<String, Object> response = new HashMap<String, Object>();
			Profesor profesor = this.profesorService.findOne(profesorId);
			
			if(profesor==null) {
				response.put("mensaje",	 "El profesor con ID: ".concat(profesorId.toString()).concat(" no existe"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(profesor.getDerechoOlvidado()==true) {
				response.put("mensaje",	 "El profesor ya ha solicitado  la peticion para ser olvidado ");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(this.profesorService.profesorTieneAlumno(profesor.getId())!=true) {
				response.put("mensaje",	 "No se pudo realizar la petición porque aún tiene horarios activos.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			try {
				profesor  =this.profesorService.peticionBorrar(profesor);
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
					
			}
			
			return new ResponseEntity<Profesor>(profesor,HttpStatus.CREATED); 
			
			
			
		}


}
