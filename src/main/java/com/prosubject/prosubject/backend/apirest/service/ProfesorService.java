package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.repository.ProfesorRepository;

@Service
public class ProfesorService {

	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	public List<Profesor> findAll() {
		return this.profesorRepository.findAll();
	}
	
	public Profesor findOne(final Long profesorId) {
		return this.profesorRepository.findById(profesorId).orElse(null);
	}
	
	public Profesor findByUserAccount(final Long userAccountId) {
		return this.profesorRepository.findProfesorByUserAccountId(userAccountId);
	}

	
	public Profesor save(final Profesor p) { 
		return this.profesorRepository.save(p);	
	}

}
