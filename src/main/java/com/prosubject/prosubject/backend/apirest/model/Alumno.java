package com.prosubject.prosubject.backend.apirest.model;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name = "alumnos")
public class Alumno implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "useraccount_id",nullable = false, unique=true)
	private UserAccount useraccount;
	
	@NotBlank
	@Column(nullable = false)
	private String nombre;
	
	@NotBlank
	@Column(nullable = false)
	private String apellido1;
	
	@NotBlank
	@Column(nullable = false)
	private String apellido2;
	
	@NotNull
	@Column
	private Integer contadorDescuento;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "universidad_nombre")
	private Universidad universidad;
	
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "facultad_nombre")
	private Facultad facultad;
	
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "grado_nombre")
	private Grado grado;
	
	@NotBlank
	@Pattern(regexp = "^(\\d{8})([A-Z])$",message = "Debe tener 8 numeros y 1 letra")
	@Column(unique = true , nullable = false)
	private String dni;
	
	@NotBlank
	@NotNull
	@Email
	@Column(unique = true ,  nullable = false)
	private String email;
	
	@Pattern(regexp="^\\d{9}|^$",message = "Debe introducir un numero de telefono correcto")
	private String telefono;
	
	@Column(nullable = false)
	private Boolean derechoOlvidado;	
	


	public Boolean getDerechoOlvidado() {
		return derechoOlvidado;
	}



	public void setDerechoOlvidado(Boolean derechoOlvidado) {
		this.derechoOlvidado = derechoOlvidado;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getContadorDescuento() {
		return contadorDescuento;
	}



	public void setContadorDescuento(Integer contadorDescuento) {
		this.contadorDescuento = contadorDescuento;
	}


	public UserAccount getUserAccount() {
		return useraccount;
	}



	public void setUserAccount(UserAccount userAccount) {
		this.useraccount = userAccount;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getApellido1() {
		return apellido1;
	}



	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public Universidad getUniversidad() {
		return universidad;
	}



	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}



	public Facultad getFacultad() {
		return facultad;
	}



	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}



	public Grado getGrado() {
		return grado;
	}



	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	




	
	

}
