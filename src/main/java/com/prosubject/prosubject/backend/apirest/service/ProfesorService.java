package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.model.ValidacionExpediente;
import com.prosubject.prosubject.backend.apirest.repository.AlumnoRepository;
import com.prosubject.prosubject.backend.apirest.repository.ProfesorRepository;

@Service
public class ProfesorService {

	
	@Autowired
	private ProfesorRepository profesorRepository;
	@Autowired
	private AlumnoRepository alumnoRepository;
	@Autowired
	private ValoracionService valoracionService;
	
	public List<Profesor> findAll() {
		return this.profesorRepository.findAll();
	}
	
	public Profesor findOne(final Long profesorId) {
		
		return this.profesorRepository.findById(profesorId).orElse(null);
	}
	
	public Profesor findByUserAccount(final Long userAccountId) {
		return this.profesorRepository.findProfesorByUserAccountId(userAccountId);
	}
	
	public Profesor findByUsername(final String username) {
		return this.profesorRepository.findProfesorByUsername(username);
	}

	
	public Profesor save(final Profesor p) { 
		
		return this.profesorRepository.save(p);	
	}
	
	public Profesor edit(Long id, Profesor profesor) {

		Profesor profe = findOne(id);
		
		if(profe.getExpendiente().getId()!=profesor.getExpendiente().getId()) {
			profe.setExpedienteValidado(ValidacionExpediente.PENDIENTE);
			profe.setExpendiente(profesor.getExpendiente());
		}

		profe.setApellido1(profesor.getApellido1());
		profe.setApellido2(profesor.getApellido2());
		profe.setDni(profesor.getDni());
		profe.setEmail(profesor.getEmail());
		profe.setNombre(profesor.getNombre());
		profe.setTelefono(profesor.getTelefono());
		profe.setTarifaPremium(profesor.getTarifaPremium());
		profe.getUserAccount().setUsername((profesor.getUserAccount().getUsername()));
		profe.getUserAccount().setPassword((profesor.getUserAccount().getPassword()));
	
		Profesor profeEditado = save(profe);

		return profeEditado;
	}
	
	
	
	public List<String> emailsProfesor() {
		return this.profesorRepository.emailsProfesor();
	}
	
	public List<String> DNIsProfesor() {
		return this.profesorRepository.DNIsProfesor();
	}
	
	public List<Profesor> profesoresExpedientePendiete() {
		return this.profesorRepository.profesoresExpedientePendiete();
	}
	
	public List<Profesor> profesoresTarifaPremium(){
		return this.profesorRepository.profesoresTarifaPremium();
	}
	
	public void valoracionMedia(Long profesorId) {
	Profesor profesor = this.profesorRepository.findById(profesorId).orElse(null);
	Double vm = this.valoracionService.valoracionMediaDeProfesor(profesorId);
	profesor.setValoracionMedia(vm);
	this.profesorRepository.save(profesor);
		
		
	}
	
	public boolean profesorTieneAlumno(Long profesorId){
		List<Alumno> alumnos= this.alumnoRepository.alumnosDeProfesor(profesorId);
		if(alumnos.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Profesor peticionBorrar(Profesor profesor) {
		Profesor prof = this.findOne(profesor.getId());
		prof.setDerechoOlvidado(true);
		return this.save(prof);
		}

}
