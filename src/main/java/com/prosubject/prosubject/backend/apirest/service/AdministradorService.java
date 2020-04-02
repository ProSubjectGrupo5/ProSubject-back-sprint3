package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.junit.Assert;
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

	public Administrador edit(Long id, Administrador administrador) {

		Administrador admin = findOne(id);

		admin.setApellido1(administrador.getApellido1());
		admin.setApellido2(administrador.getApellido2());
		admin.setDni(administrador.getDni());
		admin.setEmail(administrador.getEmail());
		admin.setNombre(administrador.getNombre());
		admin.setTelefono(administrador.getTelefono());
		admin.getUserAccount().setUsername((administrador.getUserAccount().getUsername()));
		admin.getUserAccount().setPassword((administrador.getUserAccount().getPassword()));
		
		Administrador adminEditado = save(admin);

		return adminEditado;
	}
	
	public List<String> emailsAdministradores() {
		return this.administradorRepository.emailsAdministradores();
	}
	
	public List<String> dnisAdministradores() {
		return this.administradorRepository.dnisAdministradores();
	}

}
