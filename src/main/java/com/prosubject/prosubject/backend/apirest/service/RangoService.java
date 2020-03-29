package com.prosubject.prosubject.backend.apirest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Rango;

import com.prosubject.prosubject.backend.apirest.repository.RangoRepository;


@Service
public class RangoService {

	@Autowired
	private RangoRepository rangoRepository;
	
	
	public Rango create() {
		final Rango a = new Rango();
		return a;
	}
	
	public List<Rango> findAll() {
		return this.rangoRepository.findAll();
	}
	
	public Rango findOne(final Long rangoId) {
		return this.rangoRepository.findById(rangoId).orElse(null);
	}
	
	
	public Rango save(final Rango a) {
		
		Rango saved = this.rangoRepository.save(a);
		
		return saved;
	}
	
	public List<Rango> rangosPorHorario(Long horarioId){
		return this.rangoRepository.rangosPorHorario(horarioId);
	}


	public void delete(Rango rango) {
		this.rangoRepository.delete(rango);
		
	}




}
