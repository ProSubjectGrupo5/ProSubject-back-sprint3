package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long>{
	
	@Query("select p from profesores p join p.useraccount u where u.id=?1")
	Profesor findProfesorByUserAccountId(Long id);
	
	@Query("select p from profesores p where p.useraccount.username=?1")
	Profesor findProfesorByUsername(String username);
	
	
	@Query("select p.email from profesores p")
	List<String> emailsProfesor();
	
	@Query("select p.dni from profesores p")
	List<String> DNIsProfesor();
	
	@Query("select p from profesores p where p.expedienteValidado=0")
	List<Profesor> profesoresExpedientePendiete();
	
	@Query("select p from profesores p where p.tarifaPremium=1")
	List<Profesor> profesoresTarifaPremium();

}
