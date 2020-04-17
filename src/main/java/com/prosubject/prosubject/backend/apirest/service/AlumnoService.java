package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Rango;
import com.prosubject.prosubject.backend.apirest.repository.AlumnoRepository;

@Service
public class AlumnoService {
	@Autowired
	private AlumnoRepository alumnoRepository;
	@Autowired
	private RangoService rangoService;
	
	
	public Alumno create() {
		final Alumno a = new Alumno();
		return a;
	}
	
	public List<Alumno> findAll() {
		return this.alumnoRepository.findAll();
	}
	
	public Alumno findOne(final Long alumnoId) {
		return this.alumnoRepository.findById(alumnoId).orElse(null);
	}
	
	public Alumno findByUserAccount(final Long userAccountId) {
		return this.alumnoRepository.findAlumnoByUserAccountId(userAccountId);
	}
	
	public Alumno findByUsername(final String username) {
		return this.alumnoRepository.findAlumnoByUsername(username);
	}
	

	public Alumno save(final Alumno a) {
	   Alumno saved = this.alumnoRepository.save(a);

		return saved;
	}
	
	public Alumno edit(Long id, Alumno alumno) {

		Alumno alumn = findOne(id);

		alumn.setApellido1(alumno.getApellido1());
		alumn.setApellido2(alumno.getApellido2());
		alumn.setDni(alumno.getDni());
		alumn.setEmail(alumno.getEmail());
		alumn.setNombre(alumno.getNombre());
		alumn.setTelefono(alumno.getTelefono());
		alumn.getUserAccount().setUsername((alumno.getUserAccount().getUsername()));
		alumn.getUserAccount().setPassword((alumno.getUserAccount().getPassword()));
		alumn.setUniversidad(alumno.getUniversidad());
		alumn.setFacultad(alumno.getFacultad());
		alumn.setGrado(alumno.getGrado());
	
		
		Alumno alumnoEditado = save(alumn);

		return alumnoEditado;
	}
	
	public List<Alumno> alumnosDeUnHorario(Long id) {
		return this.alumnoRepository.alumnosDeUnHorario(id);
	}
	
	public List<String> emailsAlumno() {
		return this.alumnoRepository.emailsAlumno();
	}
	
	public List<String> DNIsAlumno() {
		return this.alumnoRepository.DNIsAlumno();
	}
	
	public List<Alumno> alumnosDeUnEspacio(Long id) {
		return this.alumnoRepository.alumnosDeUnEspacio(id);
	}
	
	public List<Alumno> alumnosQueHanValoradoUnEspacio(Long espacioId) {
		return this.alumnoRepository.alumnosQueHanValoradoUnEspacio(espacioId);
	}
	
	public Alumno peticionBorrar(Alumno alumno) {
	Alumno alum = this.findOne(alumno.getId());
	alum.setDerechoOlvidado(true);
	return this.save(alum);
	}
	

	
	
}
