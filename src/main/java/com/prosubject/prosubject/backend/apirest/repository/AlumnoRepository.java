package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Alumno;

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
	


	
	
	

}
