package com.prosubject.prosubject.backend.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prosubject.prosubject.backend.apirest.model.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long>  {
	
	@Query("select r from respuestas r join r.user u where u.id=?1")
	Respuesta findRespuestaByUserAccountId(Long id);
	


}
