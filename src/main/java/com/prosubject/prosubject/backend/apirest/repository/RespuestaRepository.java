package com.prosubject.prosubject.backend.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long>  {
	
	@Query("select r from respuestas r join r.user u where u.id=?1")
	Respuesta findRespuestaByUserAccountId(Long id);
	
	@Query("select r from respuestas r where r.foro.id=?1")
	List<Respuesta> respuestaPorForoId(Long id);
	
	@Query("select r from respuestas r where r.user.id=?1")
	List<Respuesta> respuestasPorUserAccount(Long userAccountId);
	


}
