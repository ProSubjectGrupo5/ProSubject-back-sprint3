package com.prosubject.prosubject.backend.apirest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;

@Entity(name = "valoraciones")
public class Valoracion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Range(min = 0, max = 5)
	private Integer puntuacion;
	
	private String comentario;
	
	@Valid
	@ManyToOne(optional = true)
	@JoinColumn(name = "alumno_id")
	private Alumno alumno;
	
	@Valid
	@ManyToOne(optional = true)
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;

	@Valid
	@ManyToOne(optional = true)
	@JoinColumn(name = "espacio_id")
	private Espacio espacio;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Espacio getEspacio() {
		return espacio;
	}

	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
	}
	
	
	
	
	
	
	
}