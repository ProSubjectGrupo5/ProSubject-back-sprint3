package com.prosubject.prosubject.backend.apirest.service;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.repository.HorarioRepository;


@Service
public class HorarioService {
	@Autowired
	private HorarioRepository horarioRepository;
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private AlumnoService alumnoService;
	
	
	public Horario create() {
		final Horario a = new Horario();
		return a;
	}
	
	public List<Horario> findAll() {
		return this.horarioRepository.findAll();
	}
	
	public Horario findOne(final long horarioId) {
		return this.horarioRepository.findById(horarioId).orElse(null);
	}
	
	private boolean checkHoraInicioValid(Horario horario) throws Exception {
		Date hoy = new Date();
		if ( !horario.getHoraInicio().after(hoy) && !horario.getHoraInicio().before(horario.getHoraFin())) {
			throw new Exception("La fecha de inicio debe ser posterior a hoy ");
		}
		return true;
	}
	
	private boolean checkHoraFinValid(Horario horario) throws Exception {
		
		if ( (!horario.getHoraFin().after(horario.getHoraInicio()))) {
			throw new Exception("La fecha de fin debe ser posterior a la fecha de inicio");
		}
		return true;
	}

//	public List<Horario> save(Collection<Horario> h) throws Exception{
//		
//		Espacio e = h.stream().findFirst().get().getEspacio();
//		Espacio eSaved = this.espacioService.save(e);	
//		
//		for (Horario horario : h) {
//			if (horario.getId()==null) {
//				Collection<Alumno> alumnos = new HashSet<Alumno>();
//				horario.setEspacio(eSaved);
//				horario.setAlumnos(alumnos);
//				
//			}else{
//				Horario horarioAntiguo=this.findOne(horario.getId());
//				horario.setAlumnos(horarioAntiguo.getAlumnos());	
//			}
//			
//			
//			if(checkHoraInicioValid(horario)&& checkHoraFinValid(horario)) {
//				horario = this.horarioRepository.save(horario);
//			}
//			
//		}
//				
//		return this.horariosDeUnEspacio(eSaved.getId());
//	
//	}
//	
//	
//	public Horario saveOne(Horario h) throws Exception{
//		
//		Espacio e = h.getEspacio();
//		Espacio eSaved = this.espacioService.save(e);
//		
//		if (h.getId()==null) {
//			Collection<Alumno> alumnos = new HashSet<Alumno>();
//			h.setEspacio(eSaved);
//			h.setAlumnos(alumnos);
//				
//		}else{
//				Horario horarioAntiguo=this.findOne(h.getId());
//				h.setAlumnos(horarioAntiguo.getAlumnos());	
//			}
//			
//			
//			if(checkHoraInicioValid(h)&& checkHoraFinValid(h)) {
//				h = this.horarioRepository.save(h);
//			}
//			
//		
//				
//		return h;
//	
//	}
//		
//		
//	
//		public List<Horario> horariosDeUnEspacio(long espacioId) {
//			return this.horarioRepository.horariosDeUnEspacio(espacioId);
//		}
//		
//	
//		//Metodo para inscribir un alumno en un horario
//		public Horario añadirAlumno(Long horarioId, Long alumnoId) throws Exception{
//		
//			Alumno alumno = this.alumnoService.findOne(alumnoId);
//			Horario horario = this.findOne(horarioId);
//			horario.getAlumnos().add(alumno);
//			return this.horarioRepository.save(horario);
//		}
		
//		public List<Horario> horariosDeAlumno(Long alumnoId) throws Exception{
//		
//			return this.horarioRepository.horariosDeAlumno(alumnoId);
//		}
//		
		public List<Horario> horariosDeProfesor(Long profesorId) throws Exception{
			return this.horarioRepository.horariosDeProfesor(profesorId);
		}
	
		
		
		
		
	

	
}
