package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Rango;

@Repository
public interface RangoRepository extends JpaRepository<Rango, Long>{

	@Query("select r from rangos r where r.horario.id=?1")
	List<Rango> rangosPorHorario(Long id);
}
