package com.prosubject.prosubject.backend.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>  {
	
	@Query("select p from alumnos p join p.useraccount u where u.id=?1")
	Alumno findAlumnoByUserAccountId(Long id);
	
	
	

}
