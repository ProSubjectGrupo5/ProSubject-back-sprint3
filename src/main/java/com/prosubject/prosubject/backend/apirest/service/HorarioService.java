package com.prosubject.prosubject.backend.apirest.service;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if ( !horario.getFechaInicio().after(hoy) && !horario.getFechaInicio().before(horario.getFechaFin())) {
			throw new Exception("La fecha de inicio debe ser posterior a hoy ");
		}
		return true;
	}
	
	private boolean checkHoraFinValid(Horario horario) throws Exception {
		
		if ( (!horario.getFechaFin().after(horario.getFechaInicio()))) {
			throw new Exception("La fecha de fin debe ser posterior a la fecha de inicio");
		}
		return true;
	}

	public void save(Collection<Horario> h) throws Exception{
		
		Espacio e = h.stream().findFirst().get().getEspacio();
		Espacio eSaved = this.espacioService.save(e);
		
		for (Horario horario : h) {
			Collection<Alumno> alumnos = new HashSet<Alumno>();
			horario.setEspacio(eSaved);
			horario.setAlumnos(alumnos);
			
			if(checkHoraInicioValid(horario)&& checkHoraFinValid(horario)) {
				horario = this.horarioRepository.save(horario);
			}
			
		}
	}
		
	
		public List<Horario> horariosDeUnEspacio(long espacioId) {
			return this.horarioRepository.horariosDeUnEspacio(espacioId);
		}
		
	
		//Metodo para inscribir un alumno en un horario
		public Horario añadirAlumno(Long horarioId, Long alumnoId) throws Exception{
		
			Alumno alumno = this.alumnoService.findOne(alumnoId);
			Horario horario = this.findOne(horarioId);
			horario.getAlumnos().add(alumno);
			return this.horarioRepository.save(horario);
		}
		
		public List<Horario> horariosDeAlumno(Long alumnoId) throws Exception{
		
			return this.horarioRepository.horariosDeAlumno(alumnoId);
		}
		
		public List<Horario> horariosDeProfesor(Long profesorId) throws Exception{
			return this.horarioRepository.horariosDeProfesor(profesorId);
		}
	
		
		
		
		
	

	
}
