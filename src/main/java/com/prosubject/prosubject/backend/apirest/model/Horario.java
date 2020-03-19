package com.prosubject.prosubject.backend.apirest.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "horario")
public class Horario implements Serializable{
	
	
private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ManyToMany
	private Collection<Alumno> alumnos;

	@NotNull
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaInicio;


	@NotNull
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaFin;
	
	@NotNull
	private DiaSemana dia;
	
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "espacio_id")
	private Espacio espacio;
	
	@NotNull
	@Min(0)
	private Long capacidad;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Long capacidad) {
		this.capacidad = capacidad;
	}

	public Espacio getEspacio() {
		return espacio;
	}

	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
	}

	public Date getFechaInicio() {
		
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public DiaSemana getDia() {
		return dia;
	}

	public void setDia(DiaSemana dia) {
		this.dia = dia;
	}
	
	public Collection<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Collection<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


		 
}
