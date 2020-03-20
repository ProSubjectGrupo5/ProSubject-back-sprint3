package com.prosubject.prosubject.backend.apirest.model;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity(name = "profesores")
public class Profesor implements Serializable {
	

	

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
	
	@NotBlank
	@Pattern(regexp = "^(\\d{8})([A-Z])$",message = "Debe tener 8 numeros y 1 letra")
	@Column(unique = true ,nullable = false)
	private String dni;
	
	@NotBlank
	@NotNull
	@Email
	@Column(unique = true , nullable = false)
	private String email;
	
	@Pattern(regexp="^([+][1-9]\\d{0,3})?([ ])?([(][1-9]\\d{0,4}[)])?([ ])?\\d{6,10}|^$",message = "Debe introducir un numero de telefono correcto")
	private String telefono;
	
	
	@Column(nullable = false)
	private Boolean tarifaPremium;
	
	
	@Column(nullable = false)
	private Boolean expedienteValidado;
	
	
	private Byte[] expendiente;

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

	public Boolean getTarifaPremium() {
		return tarifaPremium;
	}

	public void setTarifaPremium(Boolean tarifaPremium) {
		this.tarifaPremium = tarifaPremium;
	}

	public Boolean getExpedienteValidado() {
		return expedienteValidado;
	}

	public void setExpedienteValidado(Boolean expedienteValidado) {
		this.expedienteValidado = expedienteValidado;
	}

	public Byte[] getExpendiente() {
		return expendiente;
	}

	public void setExpendiente(Byte[] expendiente) {
		this.expendiente = expendiente;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUseraccount(UserAccount userAccount) {
		this.useraccount = userAccount;
	}


	






}



