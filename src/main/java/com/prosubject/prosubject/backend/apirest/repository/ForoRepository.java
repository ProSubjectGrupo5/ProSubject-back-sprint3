package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Foro;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Long>{
	
	@Query("select e.foro from espacios e where e.id=?1")
	Foro foroPorEspacioId(Long id);

}
