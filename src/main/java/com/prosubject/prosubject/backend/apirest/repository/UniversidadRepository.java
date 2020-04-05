package com.prosubject.prosubject.backend.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Universidad;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Long>{

	@Query("select u.id from universidades u where u.nombre=?1")
	Long findUniId(String nombre);
	
}
