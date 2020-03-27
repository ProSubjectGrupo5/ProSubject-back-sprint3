package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.Carrito;
import com.prosubject.prosubject.backend.apirest.repository.CarritoRepository;

@Service
public class CarritoService {
	
	@Autowired
	private CarritoRepository carritoRepository;
	
	public List<Carrito> findAll(){
		return this.carritoRepository.findAll();
	}
	
	public Carrito findOne(final Long carritoId) {
		return this.carritoRepository.findById(carritoId).orElse(null);
	}
	
	public Carrito precioMensualHorarios(Long alumnoId) {
		Double precioHorario = this.carritoRepository.precioMensualHorarios(alumnoId);
		Integer mensual = 4;
		if(precioHorario==null) {
			precioHorario = 0.0;
		}
		Carrito c = findOne(alumnoId);
		c.setPrecioMensual(precioHorario * mensual);
		return c;
	}
	
	public Carrito save(final Carrito c) {
		Carrito saved = this.carritoRepository.save(c);
		return saved;
	}
	
}
