package com.prosubject.prosubject.backend.apirest.service;

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
	
	public List<Foro> findAll() {
		return this.foroRepository.findAll();
	}
	
	public Foro findOne(final Long foroId) {
		return this.foroRepository.findById(foroId).orElse(null);
	}

	
	public Foro save(final Foro c) { 
		return this.foroRepository.save(c);	
	}
	
	public void delete(final Foro c) { 
		 this.foroRepository.delete(c);	
	}
	
	public Foro foroPorEspacioId(final Long espacioId) { 
		return  this.foroRepository.foroPorEspacioId(espacioId);
	}
}
