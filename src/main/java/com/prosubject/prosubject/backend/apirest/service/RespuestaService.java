package com.prosubject.prosubject.backend.apirest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prosubject.prosubject.backend.apirest.model.Respuesta;
import com.prosubject.prosubject.backend.apirest.repository.RespuestaRepository;

@Service
public class RespuestaService {
	
	@Autowired
	private RespuestaRepository RespuestaRepository;
	
	
	public Respuesta create() {
		final Respuesta r = new Respuesta();
		return r;
	}
	
	public List<Respuesta> findAll() {
		return this.RespuestaRepository.findAll();
	}
	
	public Respuesta findOne(final Long respuestaId) {
		return this.RespuestaRepository.findById(respuestaId).orElse(null);
	}
	
	public Respuesta findByUserAccount(final Long userAccountId) {
		return this.RespuestaRepository.findRespuestaByUserAccountId(userAccountId);
	}
	

	public Respuesta save(final Respuesta r) {
		Date hoy = new Date();
		r.setCreacionRespuesta(hoy);
		return this.RespuestaRepository.save(r);

		 
	}

	
	
}
