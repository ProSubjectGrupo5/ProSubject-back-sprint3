
package com.prosubject.prosubject.backend.apirest.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;


@Entity(name = "horario")
public class Horario implements Serializable{
	
	
private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	


	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date horaInicio;


	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date horaFin;
	
	@Column(nullable = false)
	private DiaSemana dia;
	
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "espacio_id")
	private Espacio espacio;
	
	@Column(nullable = false)
	@Min(0)
	private Long capacidad;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fechaInicio;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fechaFin;
	
	


	
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



	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public DiaSemana getDia() {
		return dia;
	}

	public void setDia(DiaSemana dia) {
		this.dia = dia;
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
	
	
	



	
	


		 
}

