package com.prosubject.prosubject.backend.apirest.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity(name = "foros")
public class Foro {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 32)
    private String titulo;

    @Column(nullable = false, length = 1024)
    private String contenido;
    
    @Column
    private Date fechaCreacion;

    @OneToMany
    private List<Respuesta> respuestas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public String getContent() {
        return contenido;
    }

    public void setContent(String content) {
        this.contenido = content;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }


}
