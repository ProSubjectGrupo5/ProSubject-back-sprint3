package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Horario;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Long> {
	@Query("select distinct(e) from espacios e inner join e.asignatura asig "
			+ "inner join asig.curso cur "
			+ "inner join asig.grados gra "
			+ "inner join gra.facultad facul "
			+ "inner join facul.universidad uni "
			+ "where uni.nombre=?1 AND facul.nombre=?2 AND gra.nombre=?3 AND cur.nombre=?4 AND asig.nombre=?5 ")
	List<Espacio> findDisponibles(String universidad, String facultad, String grado, String curso, String asignatura);
	
	
	
	@Query("select e from espacios e where e.profesor.id=?1")
	List<Espacio> espaciosDeUnProfesor(Long id);
	
	@Query("select e from espacios e where e.profesor.id=?1 AND e.draftMode=1")
	List<Espacio> espaciosDeUnProfesorEnDraftMode(Long id);
	
//	
//	
	@Query("select r.horario.espacio from rangos r where r.alumno.id=?1")
	List<Espacio> espaciosDeUnAlumno(Long id);

	
	@Query("select distinct(h.espacio) from horario h where h.capacidad > (select count(r.alumno.id) from rangos r where r.horario.id = h.id) AND h.espacio.draftMode = 0 AND h.fechaInicio > CURRENT_TIMESTAMP")
	List<Espacio> espaciosConHorarioConCapacidad();
	
}
