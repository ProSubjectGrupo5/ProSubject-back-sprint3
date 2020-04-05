package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Grado;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {

	@Query("select distinct(g) from grados g inner join g.facultad facu "
			+ "inner join facu.universidad uni "
			+ "where uni.nombre=?1 AND facu.nombre=?2")
	List<Grado> findGradoFacu(String universidad, String facultad);

	@Query("select g.id from grados g where g.nombre=?1")
	Long findGradoId(String nombre);
	
	
}
 

