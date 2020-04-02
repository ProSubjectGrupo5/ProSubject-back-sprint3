package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Valoracion;
import com.prosubject.prosubject.backend.apirest.repository.ValoracionRepository;


@Service
public class ValoracionService {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	
	public Valoracion create() {
		final Valoracion v = new Valoracion();
		return v;
	}
	
	public List<Valoracion> findAll() {
		return this.valoracionRepository.findAll();
	}
	
	public Valoracion findOne(final Long valoracionId) {
		return this.valoracionRepository.findById(valoracionId).orElse(null);
	}
	
	
	public Valoracion save(final Valoracion v) {
		
		Valoracion saved = this.valoracionRepository.save(v);
		
		return saved;
	}
	
	
	public void delete(Valoracion valoracion) {
		
		this.valoracionRepository.delete(valoracion);
		
	}
	
	
	public List<Valoracion> valoracionesPorEspacioId(final Long espacioId) {
		return this.valoracionRepository.valoracionesDeUnEspacio(espacioId);
	}
	






}
