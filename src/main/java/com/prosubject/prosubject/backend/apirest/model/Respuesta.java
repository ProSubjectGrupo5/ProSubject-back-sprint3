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
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name = "respuestas")
public class Respuesta implements Serializable {
	

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String contenido;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date creacionRespuesta;
	
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "userAccount_id")
	private UserAccount user;
	
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "foro_id")
	private Foro foro;
	
	/*
	public Respuesta(String contenido, Date fecha, UserAccount user, Foro foro) {
		this.contenido = contenido;
		this.creacionRespuesta = fecha;
		this.user = user;
		this.foro = foro;
	}
	*/

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getCreacionRespuesta() {
		return creacionRespuesta;
	}

	public void setCreacionRespuesta(Date creacionRespuesta) {
		this.creacionRespuesta = creacionRespuesta;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}
	
	
	
}