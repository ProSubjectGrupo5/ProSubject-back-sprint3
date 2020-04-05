package com.prosubject.prosubject.backend.apirest.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.repository.EspacioRepository;

@Service
public class EspacioService {
	@Autowired
	private EspacioRepository espacioRepository;
	@Autowired
	private ForoService foroService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private HorarioService horarioService;

	
	public List<Espacio> findAll() {
		return this.espacioRepository.findAll();
	}
	
	public List<Espacio> findDisponibles(String universidad,
			String facultad, String grado,String curso, String asignatura){
		 List<Espacio> espaciosFiltrados =this.espacioRepository.findDisponibles(universidad, facultad, grado,curso, asignatura);
		//Para poner primero los profesores que han pagado el premium (Comparador)
		 Comparator<Espacio> cmp = Comparator.comparing((Espacio d) -> d.getProfesor().getTarifaPremium()).reversed();
		 List<Espacio> espacioDisponible = this.espacioRepository.espaciosConHorarioConCapacidad();
		 boolean intersacion  = espacioDisponible.retainAll(espaciosFiltrados);
		 espacioDisponible.sort(cmp);
		 return espacioDisponible;
	}
	
	public Espacio findOne(final long espacioId){
		return this.espacioRepository.findById(espacioId).orElse(null);
	}
	
	
	public Espacio save(final Espacio e) throws Exception {
		
		Foro f = new Foro();
		if(e.getId()==null) {
			
			
			f.setTitulo("Foro "+e.getAsignatura().getNombre());			
			Foro fSaved= this.foroService.save(f);
			e.setForo(fSaved);
			
		}else {
			Espacio espacioAntiguo=this.findOne(e.getId());
//			Assert.isTrue(espacioAntiguo.getDraftMode()==1,"El espacio con id "+ e.getId().toString() +
//					" no puede ser modificado, debido al draftMode");
			f=this.foroService.foroPorEspacioId(e.getId());
			e.setForo(f);
			
			
			
		
		}
		
		
		return  this.espacioRepository.save(e);
}
		

	//Listado de espacios creados por un profesor
	public List<Espacio> espaciosDeUnProfesor(Long id){
		return this.espacioRepository.espaciosDeUnProfesor(id);
	}
	
	//Listado de espacios en los que estas inscrito un alumno
		public List<Espacio> espaciosDeUnAlumno(Long id){
			return this.espacioRepository.espaciosDeUnAlumno(id);
		}

	public List<Espacio> espaciosDeUnProfesorEnDraftMode(Long id){
			return this.espacioRepository.espaciosDeUnProfesorEnDraftMode(id);
		}
	
	public List<Espacio> espaciosConHorarioConCapacidad() throws Exception{
		return this.espacioRepository.espaciosConHorarioConCapacidad();
	}
	
	public void delete(Espacio espacio) {
		
		List<Horario> horarios = this.horarioService.horariosDeUnEspacio(espacio.getId());
		for (Horario horario : horarios) {
			this.horarioService.delete(horario);
		}
		
		this.espacioRepository.delete(espacio);
		
	}
	
	
}

