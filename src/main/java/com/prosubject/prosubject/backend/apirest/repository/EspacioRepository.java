package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
// A quitar capacidad de espacio he quitado (jesus) estacondicion dde la primera query :e.capacidad > e.alumnos.size AND
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
	
	
	@Query("select h.espacio from horario h  join h.alumnos alum where alum.id=?1")
	List<Espacio> espaciosDeUnAlumno(Long id);

	@Query("select distinct(h.espacio) from horario h  where h.capacidad > h.alumnos.size")
	List<Espacio> espaciosConHorarioConCapacidad();
	
}
