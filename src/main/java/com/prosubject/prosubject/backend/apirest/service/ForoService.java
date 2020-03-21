package com.prosubject.prosubject.backend.apirest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Foro;

import com.prosubject.prosubject.backend.apirest.repository.ForoRepository;


@Service
public class ForoService {

	@Autowired
	private ForoRepository foroRepository;
	
	
	public Foro create() {
		final Foro a = new Foro();
		return a;
	}
	
	public List<Foro> findAll() {
		return this.foroRepository.findAll();
	}
	
	public Foro findOne(final Long foroId) {
		return this.foroRepository.findById(foroId).orElse(null);
	}
	
	
	public Foro save(final Foro a) {
		Date hoy = new Date();
		a.setFechaCreacion(hoy);
		Foro saved = this.foroRepository.save(a);
		
		return saved;
	}


	public void delete(Foro foro) {
		this.foroRepository.delete(foro);
		
	}


	
	public Foro foroPorEspacioId(final Long espacioId) { 
		return  this.foroRepository.foroPorEspacioId(espacioId);
	}

}
