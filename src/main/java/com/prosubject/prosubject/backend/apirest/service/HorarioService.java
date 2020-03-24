package com.prosubject.prosubject.backend.apirest.service;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Rango;
import com.prosubject.prosubject.backend.apirest.repository.HorarioRepository;


@Service
public class HorarioService {
	@Autowired
	private HorarioRepository horarioRepository;
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private RangoService rangoService;
	
	
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

	public List<Horario> save(Collection<Horario> h) throws Exception{
		
		Espacio e = h.stream().findFirst().get().getEspacio();
		Espacio eSaved = this.espacioService.save(e);	
		
		
		for (Horario horario : h) {
			if (horario.getId()==null) {
				horario.setEspacio(eSaved);
			}	
			
			if(checkHoraInicioValid(horario)&& checkHoraFinValid(horario)) {
				horario = this.horarioRepository.save(horario);
			
			
		}
		}
				
		return this.horariosDeUnEspacio(eSaved.getId());
	
	
		
	}
		
		
	

	public Horario saveOne(Horario h) throws Exception{
		
		Espacio e = h.getEspacio();
		Espacio eSaved = this.espacioService.save(e);
		
		if (h.getId()==null) {
			h.setEspacio(eSaved);
				
		}
			
		if(checkHoraInicioValid(h)&& checkHoraFinValid(h)) {
			h = this.horarioRepository.save(h);
		}
				
		
			return h;
	}
		

		public List<Horario> horariosDeUnEspacio(long espacioId) {
			return this.horarioRepository.horariosDeUnEspacio(espacioId);
		}
		//Metodo para inscribir un alumno en un horario
		public Horario añadirAlumno(Long horarioId, Long alumnoId) throws Exception{
		
			Alumno alumno = this.alumnoService.findOne(alumnoId);
			Horario horario = this.findOne(horarioId);
			Rango rango = new Rango();
			Date fechaActual =new Date();
			
			rango.setAlumno(alumno);
			rango.setFechaInicio(fechaActual);
			rango.setFechaFin(sumarRestarDiasFecha(rango.getFechaInicio(), 28));
			
			Horario saved = this.horarioRepository.save(horario);
			
			rango.setHorario(saved);
			this.rangoService.save(rango);
			
			return saved;
		}
		
		public List<Horario> horariosDeAlumno(Long alumnoId) throws Exception{
		
			return this.horarioRepository.horariosDeAlumno(alumnoId);
		}
//		
		public List<Horario> horariosDeProfesor(Long profesorId) throws Exception{
			return this.horarioRepository.horariosDeProfesor(profesorId);
		}
		
		
		public Date sumarRestarDiasFecha(Date fecha, int dias){

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(fecha); // Configuramos la fecha que se recibe

			calendar.add(Calendar.DAY_OF_YEAR, dias);

			return calendar.getTime();
			}
		
		public List<Horario> horariosNoEditablesDeUnProfesor(Long profesorId) throws Exception{
			return this.horarioRepository.horariosNoEditablesDeUnProfesor(profesorId);
		}
		
		public void delete(Horario horario) {
			this.horarioRepository.delete(horario);
			
		}
		
		
		
	
	
		
		
		
		
	

	
}
