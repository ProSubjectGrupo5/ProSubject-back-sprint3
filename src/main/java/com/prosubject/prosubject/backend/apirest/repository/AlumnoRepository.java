package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Profesor;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>  {
	
	@Query("select p from alumnos p join p.useraccount u where u.id=?1")
	Alumno findAlumnoByUserAccountId(Long id);
	

	@Query("select p from alumnos p join p.useraccount u where u.username=?1")
	Alumno findAlumnoByUsername(String username);

	@Query("select r.alumno from rangos r where r.horario.id=?1")
	List<Alumno> alumnosDeUnHorario(Long id);
	
	
	@Query("select p.email from alumnos p")
	List<String> emailsAlumno();
	
	@Query("select p.dni from alumnos p")
	List<String> DNIsAlumno();
	
	@Query("select r.alumno from rangos r where r.horario.espacio.id=?1")
	List<Alumno> alumnosDeUnEspacio(Long id);
	
	@Query("select v.alumno from valoraciones v where v.espacio.id=?1")
	List<Alumno> alumnosQueHanValoradoUnEspacio(Long id);

	@Query("select a from alumnos a where a.derechoOlvidado=1")
	List<Alumno> alumnosDerechoOlvidado();
	
	@Query("select r.alumno from rangos r where r.horario.espacio.profesor.id=?1")
	List<Alumno> alumnosDeProfesor(Long profesorId);
	

	
	

}
