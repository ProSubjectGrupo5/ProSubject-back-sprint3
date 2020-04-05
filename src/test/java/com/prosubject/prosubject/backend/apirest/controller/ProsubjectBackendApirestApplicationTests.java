package com.prosubject.prosubject.backend.apirest.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import com.prosubject.prosubject.backend.apirest.controller.AdministradorController;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Authority;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Respuesta;
import com.prosubject.prosubject.backend.apirest.model.Universidad;
import com.prosubject.prosubject.backend.apirest.model.UserAccount;
import com.prosubject.prosubject.backend.apirest.model.Valoracion;
import com.prosubject.prosubject.backend.apirest.service.AdministradorService;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.AsignaturaService;
import com.prosubject.prosubject.backend.apirest.service.CarritoService;
import com.prosubject.prosubject.backend.apirest.service.CursoService;
import com.prosubject.prosubject.backend.apirest.service.DBFileStorageService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.FacultadService;
import com.prosubject.prosubject.backend.apirest.service.ForoService;
import com.prosubject.prosubject.backend.apirest.service.GradoService;
import com.prosubject.prosubject.backend.apirest.service.HorarioService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.RespuestaService;
import com.prosubject.prosubject.backend.apirest.service.SendMailService;
import com.prosubject.prosubject.backend.apirest.service.UniversidadService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;
import com.prosubject.prosubject.backend.apirest.service.ValoracionService;

import okhttp3.Response;

import org.hibernate.exception.DataException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import javax.net.ssl.SSLEngineResult.Status;


@WebMvcTest 
@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
class ProsubjectBackendApirestApplicationTests {
	

	private static final Long TEST_ADMINISTRADOR_ID = 300L;
	private static final Long TEST_ADMINISTRADOR_ID_NEGATIVE = -300L;


	private static final Long TEST_USERACCOUNT_ID = 300L;


	private static final Long TEST_UNIVERSIDAD_ID_NEGATIVE = -300L;

	private static final Long TEST_UNIVERSIDAD_ID_POSITIVE = 300L;
	
	private static final Long TEST_VALORACION_ID_NEGATIVE = -300L;

	private static final Long TEST_VALORACION_ID_POSITIVE = 300L;
	

	private static final Long TEST_RESPUESTA_ID_NEGATIVE = -300L;

	private static final Long TEST_RESPUESTA_ID_POSITIVE = 300L;
	

	private static final Long TEST_ESPACIO_ID_NEGATIVE = -300L;

	private static final Long TEST_ESPACIO_ID_POSITIVE = 300L;


	//MOCKITO
	@Autowired
	private AdministradorController AdministradorController;
	
	@MockBean
	private AdministradorService administradorService;
	
	@MockBean
	private SendMailService sendMailService;
	
	@MockBean
	private UserAccountService userAccountService;
	
	@MockBean
	private AlumnoService alumnoService;

	@MockBean
	private ProfesorService profesorService;
	
	@MockBean
	private EspacioService espacioService;
	
	@MockBean
	private AsignaturaService asignaturaService;
	
	@MockBean
	private CarritoService carritoService;
	

	@MockBean
	private HorarioService horarioService;
	
	@MockBean
	private CursoService cursoService;
	
	@MockBean
	private ForoService foroService;


	@MockBean
	private ValoracionService valoracionService;
	
	@MockBean
	private RespuestaService respuestaService;
	
	@MockBean
	private GradoService gradoService;
	
	@MockBean
	private FacultadService facultadService;
	
	@MockBean
	private UniversidadService universidadService;
	
	@MockBean
	private DBFileStorageService dbFileStorageService;
	
	@Mock 
	private Administrador administrador;
	
	private UserAccount userAccount;
	
	private Universidad universidad;
	
	private Valoracion valoracion;
	
	private Respuesta respuesta;
	
	private Espacio espacio;
	
	@Autowired
	private MockMvc mockMvc;


	// Administrador
	//controlador
	@BeforeEach	
	void setup() {

		administrador = new Administrador();
		userAccount = new UserAccount();
		universidad = new Universidad();
		valoracion = new Valoracion();
		respuesta = new Respuesta();
		espacio = new Espacio();
		userAccount.setId(TEST_USERACCOUNT_ID);
		userAccount.setAutoridad(Authority.ADMIN);
		userAccount.setPassword("asdasdd123123131");
		userAccount.setUsername("anaromcac");
		administrador.setId(TEST_ADMINISTRADOR_ID);
		administrador.setApellido1("Romero");
		administrador.setApellido2("Caceres");
		administrador.setNombre("Ana");
		administrador.setDni("47546251F");
		administrador.setTelefono("955792206");
		administrador.setUserAccount(userAccount);
		administrador.setEmail("anaromcac@alum.us.es");
		//
		universidad.setId(TEST_UNIVERSIDAD_ID_POSITIVE);
		universidad.setNombre("Universidad de prueba");	
		Long UniId= 300L;
		//
		valoracion.setId(TEST_VALORACION_ID_POSITIVE);
		valoracion.setComentario("hola buenas");
		valoracion.setEspacio(null);
		valoracion.setProfesor(null);
		valoracion.setAlumno(null);
		valoracion.setPuntuacion(12.6);
		valoracion.getId();
		valoracion.getAlumno();
		valoracion.getComentario();
		valoracion.getEspacio();
		valoracion.getProfesor();
		valoracion.getPuntuacion();
		//
		Date d = new Date();
		respuesta.setId(TEST_RESPUESTA_ID_POSITIVE);
		respuesta.setContenido("contenidooooo");
		respuesta.setCreacionRespuesta(d);
		respuesta.setForo(null);
		respuesta.setUser(userAccount);
		respuesta.getContenido();
		respuesta.getId();
		respuesta.getCreacionRespuesta();
		respuesta.getForo();
		respuesta.getUser();
		//
		espacio.setAsignatura(null);
		espacio.setId(TEST_ESPACIO_ID_POSITIVE);
		espacio.setDraftMode(0);
		espacio.setForo(null);
		espacio.setPrecio(12.0);
		espacio.setProfesor(null);
		espacio.getAsignatura();
		espacio.getDraftMode();
		espacio.getForo();
		espacio.getId();
		espacio.getPrecio();
		espacio.getProfesor();
		
		//
		given(this.administradorService.findOne(TEST_ADMINISTRADOR_ID)).willReturn(administrador);
		given(this.userAccountService.findByUserAndPass(userAccount.getUsername(), userAccount.getPassword())).willReturn(userAccount);
		given(this.universidadService.findOne(TEST_UNIVERSIDAD_ID_POSITIVE)).willReturn(universidad);
		given(this.universidadService.findUniId(universidad.getNombre())).willReturn(UniId);
		given(this.valoracionService.findOne(TEST_VALORACION_ID_POSITIVE)).willReturn(valoracion);
		given(this.respuestaService.findOne(TEST_RESPUESTA_ID_POSITIVE)).willReturn(respuesta);
		given(this.espacioService.findOne(TEST_ESPACIO_ID_POSITIVE)).willReturn(espacio);
	}
	
	 

		
	   	@Test
	   	void testShowAdministrador() throws Exception {
	   		mockMvc.perform(get("/api/administradores/{id}",TEST_ADMINISTRADOR_ID))
	   		.andExpect(status().isOk())
	   		.andExpect(jsonPath("$.id", is(300)))
	   		.andExpect(jsonPath("$.apellido1", is("Romero")))
	   		.andExpect(jsonPath("$.apellido2", is("Caceres")))
	   		.andExpect(jsonPath("$.nombre", is("Ana")))
	   		.andExpect(jsonPath("$.dni", is("47546251F")))
	   		.andExpect(jsonPath("$.telefono", is("955792206")))
	   		.andExpect(jsonPath("$.email", is("anaromcac@alum.us.es")))
	   		.andExpect(status().is2xxSuccessful());
	   	}
	   	
		@Test
	  	void testListAdministrador() throws Exception {
	  		mockMvc.perform(get("/api/administradores")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	   	@Test
	   	void testShowNegativeAdministrador() throws Exception {
	   		mockMvc.perform(get("/api/administradores/{id}",TEST_UNIVERSIDAD_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	
	   	//da un error
	/*   	@Test
		void testProcessAdministradorCreationFormSuccess() throws Exception {
	   		this.administradorService.emailsAdministradores();
			mockMvc.perform(post("/api/administradores")
					.param("nombre", "Ana")
					.param("apellido1", "Romero")
					.param("apellido2", "Caceres")
					.param("dni","47546251F" )
					.param("telefono", "955792206")
					.param("email", "anaromcac@alum.us.es"))
					.andExpect(status().is2xxSuccessful());
					
		}*/
	   	
	  /*	@Test
			void testProcessAdministradorCreationFormFailed() throws Exception {
				mockMvc.perform(post("/api/administradores")
						.queryParam("$.nombre", "Antonio")
						.queryParam("$.apellido1", "Romero")
						.queryParam("$.apellido2", "Caceres")
						.queryParam("$.dni","47546251F" )
						.queryParam("$.telefono", "955792207")
						.queryParam("$.email", "antromcac1@gmail.com"))
						.andExpect(status().is4xxClientError()).andDo(print());
						
			}*/


	   	//UNIVERSIDAD
	   	@Test
	   	void testShowNegativeUniversidad() throws Exception {
	   		mockMvc.perform(get("/api/universidades/{id}",TEST_UNIVERSIDAD_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	
		@Test
	   	void testShowPositiveUniversidad() throws Exception {
	   		mockMvc.perform(get("/api/universidades/{id}",TEST_UNIVERSIDAD_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		
		@Test
	  	void testListUniversidad() throws Exception {
	  		mockMvc.perform(get("/api/universidades")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

		
		/*@Test
	  	void testCatchUniversidad() throws Exception {
			
			Mockito.when(this.universidadService.findOne(TEST_UNIVERSIDAD_ID_POSITIVE))
			.thenThrow(DataAccessException.class);
			JdbcTemplate template = Mockito.mock(JdbcTemplate.class);
			Mockito.when(template.query(Mockito.anyString(), (RowMapper<Universidad>)  Mockito.any(RowMapper.class))).thenThrow(DataAccessException.class);

	  		
		  }

		@Test
	   	void testShowNegativeUniversidadUni() throws Exception {
	   		mockMvc.perform(get("/api/universidades/uniId"))
	   		.andExpect(status().is2xxSuccessful());
	   	}*/
		//VALORACION
	 	@Test
	   	void testShowNegativeValoracion() throws Exception {
	   		mockMvc.perform(get("/api/valoraciones/{id}",TEST_VALORACION_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListValoracion() throws Exception {
	  		mockMvc.perform(get("/api/valoraciones")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveValoracion() throws Exception {
	   		mockMvc.perform(get("/api/valoraciones/{id}",TEST_VALORACION_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		
		//RESPUESTA
		
		@Test
	   	void testShowNegativeRespuesta() throws Exception {
	   		mockMvc.perform(get("/api/respuestas/{id}",TEST_RESPUESTA_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListRespuesta() throws Exception {
	  		mockMvc.perform(get("/api/respuestas")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveRespuesta() throws Exception {
	   		mockMvc.perform(get("/api/respuestas/{id}",TEST_RESPUESTA_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		//ESPACIO
		
		@Test
	   	void testShowNegativeEspacio() throws Exception {
	   		mockMvc.perform(get("/api/espacios/{id}",TEST_ESPACIO_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListEspacio() throws Exception {
	  		mockMvc.perform(get("/api/espacios")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveEspacio() throws Exception {
	   		mockMvc.perform(get("/api/espacios/{id}",TEST_ESPACIO_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}

	  
}