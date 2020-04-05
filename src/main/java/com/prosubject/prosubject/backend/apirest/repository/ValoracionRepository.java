package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{

	@Query("select v from valoraciones v where v.espacio.id=?1")
	List<Valoracion> valoracionesDeUnEspacio(Long id);
	
	@Query("select AVG(v.puntuacion) from valoraciones v where v.espacio.id=?1")
	Double valoracionMediaDeEspacio(Long espacioId);
	
	@Query("select AVG(v.puntuacion) from valoraciones v where v.profesor.id=?1")
	Double valoracionMediaDeProfesor(Long profesorId);
	

}
