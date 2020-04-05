package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Universidad;
import com.prosubject.prosubject.backend.apirest.repository.UniversidadRepository;

@Service
public class UniversidadService {
	@Autowired
	private UniversidadRepository universidadRepository;
	
	public Universidad create() {
		final Universidad u = new Universidad();
		return u;
	}
	
	public List<Universidad> findAll() {
		return this.universidadRepository.findAll();
	}
	
	public Universidad findOne(final long universidadId) {
		return this.universidadRepository.findById(universidadId).orElse(null);
	}
	
	public Long findUniId(String nombreUni) {
		return this.universidadRepository.findUniId(nombreUni);
	}
	
	public Universidad save(final Universidad u) {
		return this.universidadRepository.save(u);
	}
}
