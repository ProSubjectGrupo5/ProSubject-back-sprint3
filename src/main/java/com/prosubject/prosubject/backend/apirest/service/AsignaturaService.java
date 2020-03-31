package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Asignatura;
import com.prosubject.prosubject.backend.apirest.repository.AsignaturaRepository;

@Service
public class AsignaturaService {
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	public List<Asignatura> findAll() {
		return this.asignaturaRepository.findAll();
	}
	
	public Asignatura findOne(final Long asignaturaId) {
		return this.asignaturaRepository.findById(asignaturaId).orElse(null);
	}
	
	public Asignatura getAsignaturaPorNombre(String nombre) {
		return this.asignaturaRepository.getAsignaturaPorNombre(nombre);
	}
	
	public List<Asignatura> findListaAsignaturas(String universidad, 
			String facultad, String grado, String curso){
		return this.asignaturaRepository.findListaAsignaturas(universidad, facultad, grado, curso);
	}

	public Asignatura save(final Asignatura a) {
		return this.asignaturaRepository.save(a);
	}

}
