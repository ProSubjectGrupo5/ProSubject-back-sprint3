package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Carrito;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.repository.AdministradorRepository;
import com.prosubject.prosubject.backend.apirest.repository.AlumnoRepository;
import com.prosubject.prosubject.backend.apirest.repository.ProfesorRepository;

@Service
public class AdministradorService {
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private CarritoService carritoService;
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private DBFileStorageService DBFileStorageService;
	
	@Autowired
	private EspacioService espacioService;

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
	
	public Administrador findByUsername(String username) {
		return this.administradorRepository.findAdministradorByUsername(username);
		
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
	
	public List<Profesor> profesoresOlvidados() {
		return this.profesorRepository.profesoresDerechoOlvidado();
	}
	
	public List<Alumno> alumnosOlvidados() {
		return this.alumnoRepository.alumnosDerechoOlvidado();
	}
	
	public void eliminarProfesor(Long profesorId) {
		
		Profesor profesor = this.profesorService.findOne(profesorId);
		if(profesor.getExpendiente()!=null) {
		DBFile expediente = this.DBFileStorageService.findOne(profesor.getExpendiente().getId());
		this.DBFileStorageService.delete(expediente);
		}
		List<Espacio> espacios = this.espacioService.espaciosDeUnProfesor(profesorId);
		
		for (Espacio espacio : espacios) {
			this.espacioService.delete(espacio);
		}
		this.profesorRepository.delete(profesor);
	
		
	}
	
	public void eliminarAlumno(Long alumnoId) {
		
		Alumno alumno = this.alumnoService.findOne(alumnoId);
		Carrito carro = this.carritoService.carritoPorAlumno(alumnoId);
		this.carritoService.delete(carro);
		this.alumnoRepository.delete(alumno);
		
	
		
	}

	
	

}
