package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.repository.AdministradorRepository;


@Service
public class AdministradorService {
	@Autowired
	private AdministradorRepository administradorRepository;
	
	
	public Administrador create() {
		final Administrador a = new Administrador();
		return a;
	}
	
	public List<Administrador> findAll() {
		return this.administradorRepository.findAll();
	}
	
	public Administrador findOne(final Long administradorId) {
		return this.administradorRepository.findById(administradorId).orElse(null);
	}
	
	public Administrador findByUserAccount(final Long userAccountId) {
		return this.administradorRepository.findAdministradorByUserAccountId(userAccountId);
	}
	

	public Administrador save(final Administrador a) {
		
	   Administrador saved = this.administradorRepository.save(a);

		return saved;
	}

	
	
}
