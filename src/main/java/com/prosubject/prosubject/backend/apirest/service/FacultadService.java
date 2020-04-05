package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Facultad;
import com.prosubject.prosubject.backend.apirest.repository.FacultadRepository;

@Service
public class FacultadService {

	@Autowired
	private FacultadRepository facultadRepository;
	
	public List<Facultad> findAll() {
		return this.facultadRepository.findAll();
	}
	
	public Facultad findOne(final Long facultadId) {
		return this.facultadRepository.findById(facultadId).orElse(null);
	}

	public List<Facultad> findFacuUni(String universidad) {
		return this.facultadRepository.findFacuUni(universidad);
	}
	
	public Long findFacuId(String nombreFacu) {
		return this.facultadRepository.findFacuId(nombreFacu);
	}
	
	public Facultad save(final Facultad c) { 
		return this.facultadRepository.save(c);	
	}
	
}
