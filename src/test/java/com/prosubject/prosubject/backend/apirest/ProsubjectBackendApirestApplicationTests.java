package com.prosubject.prosubject.backend.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prosubject.prosubject.backend.apirest.controller.AdministradorController;
import com.prosubject.prosubject.backend.apirest.controller.AlumnoController;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Asignatura;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.repository.AdministradorRepository;
import com.prosubject.prosubject.backend.apirest.repository.AlumnoRepository;
import com.prosubject.prosubject.backend.apirest.repository.AsignaturaRepository;
import com.prosubject.prosubject.backend.apirest.repository.EspacioRepository;




@RunWith(SpringRunner.class)
@SpringBootTest
class ProsubjectBackendApirestApplicationTests {
	
     @Autowired
     private AdministradorRepository adminRep;
     
	 @Autowired
	 private AdministradorController adminController;
	 
	 @Autowired
	 private AlumnoRepository alumRep;
	 
	 @Autowired
	 private AlumnoController alumController;
	 
	 @Autowired
	 private AsignaturaRepository asigRep;
	 
	 @Autowired
	 private EspacioRepository espRep;
	
	
	@Test
    public void adminControllerTest() {
		assertThat(adminController).isNotNull();
    }
	
	//Administrador
	@Test
	public void findAdministradorByUserAccountIdTest() {
		String nombre = "Antonio";
		Long id = (long) 1;
		Administrador a = adminRep.findAdministradorByUserAccountId(id);
		assertEquals(a.getNombre(), nombre);
		
	}
	
	//Alumno
	//Para ver si se crean bien los controladores
		@Test
		public void alumControllerTest() {
			assertThat(alumController).isNotNull();
		} 
		
		@Test
		public void findAlumnoByUserAccountIdTest() {
			String nombre = "Fernando";
			Long id = (long) 2;
			Alumno aux  = alumRep.findAlumnoByUserAccountId(id);
			assertEquals(aux.getNombre(), nombre);
		}
	
		
		//Asignatura
		@Test
		public void findListaAsignaturasTest() {
			String uni = "Universidad de Sevilla";
			String facultad = "Escuela Técnica Superior de Ingeniería Informática";
			String grado = "Ingeniería del Software";
			String curso = "PRIMERO";
			List<Asignatura> res = asigRep.findListaAsignaturas(uni, facultad, grado, curso);
			assertThat(res).isNotEmpty();
			assertEquals(res.get(0).getNombre(),"Fundamentos de programación");
		}
		
		//Espacios
		@Test 
		public void findDisponiblesTest() {
			String uni = "Universidad de Sevilla";
			String facultad = "Escuela Técnica Superior de Ingeniería Informática";
			String grado = "Ingeniería del Software";
			String curso = "PRIMERO";
			String asignatura ="Fundamentos de programación";
			List<Espacio> res = espRep.findDisponibles(uni, facultad, grado, curso, asignatura);
			assertThat(res).isNotNull();
		
		}

}
