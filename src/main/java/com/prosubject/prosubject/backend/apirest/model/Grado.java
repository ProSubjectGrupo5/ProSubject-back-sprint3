package com.prosubject.prosubject.backend.apirest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


@Entity(name = "grados")
public class Grado implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nombre;
	
	@Valid
	@ManyToOne(optional=false)
	@JoinColumn(name = "facultad_id")
	private Facultad facultad;
	
	@Range(min=1, max=5)
	@Column(nullable = false)
	private Long numerocursos;
	
	
	public Long getNumerocursos() {
		return numerocursos;
	}

	public void setNumerocursos(Long numerocursos) {
		this.numerocursos = numerocursos;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;
	

}	
