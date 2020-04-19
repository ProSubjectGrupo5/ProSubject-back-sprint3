package com.prosubject.prosubject.backend.apirest.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity(name = "carrito")
public class Carrito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@OneToOne(optional = false)
	@JoinColumn(name = "alumno_id")
	private Alumno alumno;
	
	@Valid
	@ManyToMany
	private Collection<Horario> horario;
	
	@Column(nullable = false)
	@Min(0)
	private Double precioMensual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Collection<Horario> getHorario() {
		return horario;
	}

	public void setHorario(Collection<Horario> horario) {
		this.horario = horario;
	}

	public Double getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(Double precioMensual) {
		this.precioMensual = precioMensual;
	}

//	public List<Carrito> getHorario() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	
}
