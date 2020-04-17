package com.prosubject.prosubject.backend.apirest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Carrito;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.repository.CarritoRepository;
import com.prosubject.prosubject.backend.apirest.repository.HorarioRepository;

@Service
public class CarritoService {
	
	@Autowired
	private CarritoRepository carritoRepository;
	
	@Autowired
	private AlumnoService alumnoService;
	
	public List<Carrito> findAll(){
		return this.carritoRepository.findAll();
	}
	
	public Carrito findOne(final Long carritoId) {
		return this.carritoRepository.findById(carritoId).orElse(null);
	}
	
//	public Alumno contadorHorarios(Long alumId) {
//		Alumno alum = alumnoService.findOne(alumId);
//		Integer contador = this.carritoRepository.contadorHorarios(alumId);
//		Integer contadorActual = alum.getContadorDescuento();
//		alum.setContadorDescuento(contadorActual + contador);
//		this.alumnoService.save(alum);
//		return alum;
//	}
	
	public Carrito precioMensualHorarios(Long alumnoId) {
		Double precioHorario = this.carritoRepository.precioMensualHorarios(alumnoId);
		Integer mensual = 4;
		if(precioHorario==null) {
			precioHorario = 0.0;
		}
		Carrito c = findOne(alumnoId);
		Alumno alum = alumnoService.findOne(alumnoId);
		c.setPrecioMensual(precioHorario * mensual);
		if(alum.getContadorDescuento()>4) {
			Double precio = c.getPrecioMensual();
			Double descuento = precio*0.9;
			c.setPrecioMensual(descuento);
		}
		return c;
	}
	
	public Carrito save(final Carrito c) {
		Carrito saved = this.carritoRepository.save(c);
		return saved;
	}
	
	public Carrito addHorario(final Carrito carro, Horario horario) {
		carro.getHorario().add(horario);
	    return save(carro);
	}
	
	public Carrito removeHorario(final Carrito carro, Horario horario) {
		carro.getHorario().remove(horario);
	    return save(carro);
	}
	
	public Carrito removeAllHorario(final Alumno alumno) {
		Carrito carro = this.carritoRepository.carritoAlumno(alumno.getId());
		List<Horario> listaHorario = new ArrayList<Horario>();
		carro.setHorario(listaHorario);
	    return save(carro);
	}
	
	public Carrito carritoPorAlumno(final Long alumnoId) {
		return this.carritoRepository.carritoAlumno(alumnoId);
	}
	
	public void delete(final Carrito carro) {
		this.carritoRepository.delete(carro);
	}
	
}
