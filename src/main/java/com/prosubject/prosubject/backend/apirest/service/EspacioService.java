package com.prosubject.prosubject.backend.apirest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.repository.EspacioRepository;

@Service
public class EspacioService {
	@Autowired
	private EspacioRepository espacioRepository;
	@Autowired
	private ForoService foroService;
	@Autowired
	private AlumnoService alumnoService;

	
	public List<Espacio> findAll() {
		return this.espacioRepository.findAll();
	}
	
	public List<Espacio> findDisponibles(String universidad, 
			String facultad, String grado,String curso, String asignatura){
		 List<Espacio> espaciosFiltrados =this.espacioRepository.findDisponibles(universidad, facultad, grado,curso, asignatura);
		 List<Espacio> espacioDisponible = this.espacioRepository.espaciosConHorarioConCapacidad();
		 boolean intersacion  = espacioDisponible.retainAll(espaciosFiltrados);
		 return espacioDisponible;
	}
	
	public Espacio findOne(final long espacioId){
		return this.espacioRepository.findById(espacioId).orElse(null);
	}
	
	
	public Espacio save(final Espacio e) throws Exception {
		
		
		if(e.getId()==null) {
			
			Foro f = new Foro();
			f.setTitulo("Foro "+e.getAsignatura().getNombre());			
			Foro fSaved= this.foroService.save(f);
			
			e.setForo(fSaved);
			
		}
		
		
		return this.espacioRepository.save(e);
}
		
	//Metodo para inscribir un alumno en un espacio
//	public Espacio añadirAlumno(Long espacioId, Long alumnoId) throws Exception{
//		/*
//		Alumno a = this.alumnoService.findOne(alumnoId);
//		Espacio e = this.findOne(espacioId);
//		Collection<Alumno> alumnos = e.getAlumnos();
//		Assert.isTrue(!(alumnos.contains(a)));
//		Assert.isTrue(e.getCapacidad()>e.getAlumnos().size());
//		alumnos.add(a);
//		e.setAlumnos(alumnos);
//		this.save(e);
//		
//		return e;
//		*/
//		Alumno alumno = this.alumnoService.findOne(alumnoId);
//		Espacio espacio = this.findOne(espacioId);
//	//	espacio.getAlumnos().add(alumno);
//		return this.save(espacio);
//	}
	
	//Listado de espacios creados por un profesor
	public List<Espacio> espaciosDeUnProfesor(Long id){
		return this.espacioRepository.espaciosDeUnProfesor(id);
	}
	
	//Listado de espacios en los que estas inscrito un alumno
		public List<Espacio> espaciosDeUnAlumno(Long id){
			return this.espacioRepository.espaciosDeUnAlumno(id);
		}
		
		public List<Espacio> espaciosConCapacidad(){
			return this.espacioRepository.espaciosConHorarioConCapacidad();
		}
	
	
	
}

