package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Facultad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long>{
	
	@Query("select distinct(f) from facultades f inner join f.universidad uni "
			+ "where uni.nombre=?1")
	List<Facultad> findFacuUni(String universidad);
	
	@Query("select f.id from facultades f where f.nombre=?1")
	Long findFacuId(String nombreFacu);
}