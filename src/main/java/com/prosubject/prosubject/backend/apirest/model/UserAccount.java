package com.prosubject.prosubject.backend.apirest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity(name = "useraccounts")
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	@Column(unique = true)
	private String username;
	
	@NotBlank
	@NotNull
	private String password;
	
	@NotNull
	private Authority autoridad;

	
	
	
	public Authority getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(Authority autoridad) {
		this.autoridad = autoridad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsurname() {
		return username;
	}

	public void setUsurname(String user) {
		username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		password = pass;
	}
	
	

}
