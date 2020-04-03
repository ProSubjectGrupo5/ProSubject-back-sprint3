package com.prosubject.prosubject.backend.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;

import com.prosubject.prosubject.backend.apirest.controller.AdministradorController;
import com.prosubject.prosubject.backend.apirest.controller.AlumnoController;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Asignatura;
import com.prosubject.prosubject.backend.apirest.model.Authority;
import com.prosubject.prosubject.backend.apirest.model.Curso;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.DiaSemana;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Facultad;
import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.model.Grado;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.model.Rango;
import com.prosubject.prosubject.backend.apirest.model.Respuesta;
import com.prosubject.prosubject.backend.apirest.model.Universidad;
import com.prosubject.prosubject.backend.apirest.model.UserAccount;
import com.prosubject.prosubject.backend.apirest.repository.AdministradorRepository;
import com.prosubject.prosubject.backend.apirest.repository.AlumnoRepository;
import com.prosubject.prosubject.backend.apirest.repository.AsignaturaRepository;
import com.prosubject.prosubject.backend.apirest.repository.EspacioRepository;
import com.prosubject.prosubject.backend.apirest.service.AdministradorService;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.ForoService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.hamcrest.Matchers.hasProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;

import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(controllers=AdministradorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =  WebMvcConfigurer.class))
@RunWith(SpringRunner.class)
class ProsubjectBackendApirestApplicationTestsController {
	

	private static final long TEST_ADMINISTRADOR_ID = 300;


	//MOCKITO
	
	@MockBean
	private AdministradorService administradorService;
	
	@MockBean
	private UserAccountService userAccountService;
	
	@MockBean
	private AlumnoService alumnoService;

	@MockBean
	private ProfesorService profesorService;
	
	@Mock 
	private Administrador administrador;
	private UserAccount userAccount;
	
	@Autowired
	private MockMvc mockMvc;


	// Administrador
	//controlador
	@BeforeEach	
	void setup() {

		administrador = new Administrador();
		userAccount = new UserAccount();
		administrador.setId(TEST_ADMINISTRADOR_ID);
		administrador.setApellido1("Romero");
		administrador.setApellido2("Caceres");
		administrador.setNombre("Ana");
		administrador.setDni("47546251F");
		administrador.setTelefono("955792206");
		administrador.setUserAccount(userAccount);
		administrador.setEmail("anaromcac@alum.us.es");
		given(this.administradorService.findOne(TEST_ADMINISTRADOR_ID)).willReturn(administrador);
	}
	
	 
		
	   	@Test
	   	void testShowAdministrador() throws Exception {
	   		mockMvc.perform(get("/api/administradores/{id}",TEST_ADMINISTRADOR_ID))
	   		
	   		//.content("{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
	   		.andExpect(status().isOk()).andReturn().getResponse();
	   	}
	

	  
}