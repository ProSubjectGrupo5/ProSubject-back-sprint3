package com.prosubject.prosubject.backend.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{
	
	@Query("select SUM(e.precio) from carrito c "
			+ "inner join c.horario h "
			+ "inner join h.espacio e "
			+ "where c.alumno.id=?1") 
	Double precioMensualHorarios(Long id);
	
	
}
