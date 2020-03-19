package com.prosubject.prosubject.backend.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long>{
	
	@Query("select p from profesores p join p.useraccount u where u.id=?1")
	Profesor findProfesorByUserAccountId(Long id);

}
