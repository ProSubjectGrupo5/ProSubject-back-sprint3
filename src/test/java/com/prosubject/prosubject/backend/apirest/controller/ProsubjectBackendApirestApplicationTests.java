package com.prosubject.prosubject.backend.apirest.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Asignatura;
import com.prosubject.prosubject.backend.apirest.model.Authority;
import com.prosubject.prosubject.backend.apirest.model.Carrito;
import com.prosubject.prosubject.backend.apirest.model.Curso;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.DiaSemana;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Facultad;
import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.model.Grado;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
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
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;


@WebMvcTest 
@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
class ProsubjectBackendApirestApplicationTests {
	

	private static final Long TEST_ID_POSITIVE = 300L;
	private static final Long TEST_ID_NEGATIVE = -300L;



	//MOCKITO
	
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
	
	private Alumno alumno;
	
	private Asignatura asignatura;
	
	private Carrito carrito;
	
	private Curso curso;
	
	private Facultad facultad;
	
	private DBFile dbFile;
	
	private Foro foro;
	
	private Grado grado;
	
	private Horario horario;
	
	private Profesor profesor;
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
		alumno = new Alumno();
		carrito = new Carrito();
		asignatura = new Asignatura();
		curso = new Curso();
		facultad = new Facultad();
		dbFile = new DBFile();
		foro = new Foro();
		grado  = new Grado();
		horario = new Horario();
		profesor = new Profesor();
		userAccount.setId(TEST_ID_POSITIVE);
		userAccount.setAutoridad(Authority.ADMIN);
		userAccount.setPassword("asdasdd123123131");
		userAccount.setUsername("anaromcac");
		administrador.setId(TEST_ID_POSITIVE);
		administrador.setApellido1("Romero");
		administrador.setApellido2("Caceres");
		administrador.setNombre("Ana");
		administrador.setDni("47546251F");
		administrador.setTelefono("955792206");
		administrador.setUserAccount(userAccount);
		administrador.setEmail("anaromcac@alum.us.es");
		//
		universidad.setId(TEST_ID_POSITIVE);
		universidad.setNombre("Universidad de prueba");	
		Long UniId= 300L;
		//
		valoracion.setId(TEST_ID_POSITIVE);
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
		respuesta.setId(TEST_ID_POSITIVE);
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
		espacio.setId(TEST_ID_POSITIVE);
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
		
		alumno.setId(TEST_ID_POSITIVE);
		alumno.setApellido1("Romta");
		alumno.setApellido2("Delphi");
		alumno.setDni("47546250Y");
		alumno.setEmail("antonio@gmail.com");
		alumno.setFacultad(null);
		alumno.setGrado(null);
		alumno.setNombre("Ana");
		alumno.setTelefono("954792204");
		alumno.setUniversidad(null);
		alumno.setUserAccount(null);
		
		alumno.getId();
		alumno.getApellido1();
		alumno.getApellido2();
		alumno.getDni();
		alumno.getEmail();
		alumno.getFacultad();
		alumno.getGrado();
		alumno.getNombre();
		alumno.getTelefono();
		alumno.getUniversidad();
		alumno.getUserAccount();
		//
		asignatura.setId(TEST_ID_POSITIVE);
		asignatura.setNombre("Fundamentos de programacion");
		asignatura.setGrados(null);
		asignatura.setCurso(null);
		asignatura.getId();
		asignatura.getCurso();
		asignatura.getGrados();
		asignatura.getNombre();
		//
		carrito.setId(TEST_ID_POSITIVE);
		carrito.setHorario(null);
		carrito.setAlumno(null);
		carrito.setPrecioMensual(12.0);
		carrito.getId();
		carrito.getHorario();
		carrito.getAlumno();
		carrito.getPrecioMensual();
		//
		curso.setId(TEST_ID_POSITIVE);
		curso.setNombre("PRIMERO");
		curso.getNombre();
		curso.getId();
		//
		facultad.setId(TEST_ID_POSITIVE);
		facultad.setNombre("Escuela tecnica..");
		facultad.setUniversidad(null);
		facultad.getId();
		facultad.getNombre();
		facultad.getUniversidad();
		//
		dbFile.setId(TEST_ID_POSITIVE);
		dbFile.setFileName("pdf");
		dbFile.setFileType(null);
		dbFile.setData(null);
		dbFile.getId();
		dbFile.getFileName();
		dbFile.getData();
		dbFile.getFileType();
		//
		foro.setId(TEST_ID_POSITIVE);
		foro.setTitulo("Complemento..");
		foro.setFechaCreacion(null);
		foro.getId();
		foro.getTitulo();
		foro.getFechaCreacion();
		//
		grado.setId(TEST_ID_POSITIVE);
		grado.setNombre("nombre grado");
		grado.setNumerocursos(10);
		grado.setFacultad(null);
		grado.getFacultad();
		grado.getId();
		grado.getNombre();
		grado.getNumerocursos();
		//
		horario.setId(TEST_ID_POSITIVE);
		horario.setHoraInicio(d);
		horario.setHoraFin(null);
		horario.setFechaInicio(d);
		horario.setFechaFin(null);
		horario.setEspacio(null);
		horario.setDia(DiaSemana.Domingo);
		horario.setCapacidad(12L);
		horario.getId();
		horario.getHoraInicio();
		horario.getHoraFin();
		horario.getFechaInicio();
		horario.getFechaFin();
		horario.getEspacio();
		horario.getDia();
		horario.getCapacidad();
		//
		profesor.setId(TEST_ID_POSITIVE);
		profesor.setApellido1("Romero");
		profesor.setApellido2("Caceres");
		profesor.setDni("47546251F");
		profesor.setEmail("probando@gmail.com");
		profesor.setExpedienteValidado(null);
		profesor.setExpendiente(null);
		profesor.setNombre("Antrom");
		profesor.setTarifaPremium(true);
		profesor.setTelefono("603552745");
		profesor.setUserAccount(null);
		profesor.setValoracionMedia(5.0);
		profesor.getApellido1();
		profesor.getApellido2();
		profesor.getDni();
		profesor.getEmail();
		profesor.getExpedienteValidado();
		profesor.getExpendiente();
		profesor.getId();
		profesor.getNombre();
		profesor.getTarifaPremium();
		profesor.getTelefono();
		profesor.getUserAccount();
		profesor.getValoracionMedia();
		
		//
		given(this.administradorService.findOne(TEST_ID_POSITIVE)).willReturn(administrador);
		given(this.userAccountService.findByUserAndPass(userAccount.getUsername(), userAccount.getPassword())).willReturn(userAccount);
		given(this.universidadService.findOne(TEST_ID_POSITIVE)).willReturn(universidad);
		given(this.universidadService.findUniId(universidad.getNombre())).willReturn(UniId);
		given(this.valoracionService.findOne(TEST_ID_POSITIVE)).willReturn(valoracion);
		given(this.respuestaService.findOne(TEST_ID_POSITIVE)).willReturn(respuesta);
		given(this.espacioService.findOne(TEST_ID_POSITIVE)).willReturn(espacio);
		given(this.alumnoService.findOne(TEST_ID_POSITIVE)).willReturn(alumno);
		given(this.asignaturaService.findOne(TEST_ID_POSITIVE)).willReturn(asignatura);
		given(this.carritoService.findOne(TEST_ID_POSITIVE)).willReturn(carrito);
		given(this.cursoService.findOne(TEST_ID_POSITIVE)).willReturn(curso);
		given(this.facultadService.findOne(TEST_ID_POSITIVE)).willReturn(facultad);
		given(this.dbFileStorageService.findOne(TEST_ID_POSITIVE)).willReturn(dbFile);
		given(this.foroService.findOne(TEST_ID_POSITIVE)).willReturn(foro);
		given(this.gradoService.findOne(TEST_ID_POSITIVE)).willReturn(grado);
		given(this.horarioService.findOne(TEST_ID_POSITIVE)).willReturn(horario);
		given(this.profesorService.findOne(TEST_ID_POSITIVE)).willReturn(profesor);
	}
	
	 

		
	   	@Test
	   	void testShowAdministrador() throws Exception {
	   		mockMvc.perform(get("/api/administradores/{id}",TEST_ID_POSITIVE))
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
	   		mockMvc.perform(get("/api/administradores/{id}",TEST_ID_NEGATIVE))
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
	   		mockMvc.perform(get("/api/universidades/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	
		@Test
	   	void testShowPositiveUniversidad() throws Exception {
	   		mockMvc.perform(get("/api/universidades/{id}",TEST_ID_POSITIVE))
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
	   		mockMvc.perform(get("/api/valoraciones/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListValoracion() throws Exception {
	  		mockMvc.perform(get("/api/valoraciones")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveValoracion() throws Exception {
	   		mockMvc.perform(get("/api/valoraciones/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		
		//RESPUESTA
		
		@Test
	   	void testShowNegativeRespuesta() throws Exception {
	   		mockMvc.perform(get("/api/respuestas/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListRespuesta() throws Exception {
	  		mockMvc.perform(get("/api/respuestas")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveRespuesta() throws Exception {
	   		mockMvc.perform(get("/api/respuestas/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		//ESPACIO
		
		@Test
	   	void testShowNegativeEspacio() throws Exception {
	   		mockMvc.perform(get("/api/espacios/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListEspacio() throws Exception {
	  		mockMvc.perform(get("/api/espacios")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveEspacio() throws Exception {
	   		mockMvc.perform(get("/api/espacios/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		
		//ALUMNO
		
		@Test
	   	void testShowNegativeAlumno() throws Exception {
	   		mockMvc.perform(get("/api/alumnos/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListAlumno() throws Exception {
	  		mockMvc.perform(get("/api/alumnos")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveAlumno() throws Exception {
	   		mockMvc.perform(get("/api/alumnos/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		//ASIGNATURA
		
		@Test
	   	void testShowNegativeAsignatura() throws Exception {
	   		mockMvc.perform(get("/api/asignaturas/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListAsignatura() throws Exception {
	  		mockMvc.perform(get("/api/asignaturas")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveAsignatura() throws Exception {
	   		mockMvc.perform(get("/api/asignaturas/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}

		//CARRITO
		
		@Test
	   	void testShowNegativeCarrito() throws Exception {
	   		mockMvc.perform(get("/api/carrito/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListCarrito() throws Exception {
	  		mockMvc.perform(get("/api/carrito")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveCarrito() throws Exception {
	   		mockMvc.perform(get("/api/carrito/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
	  //CURSO
		
		@Test
	   	void testShowNegativeCurso() throws Exception {
	   		mockMvc.perform(get("/api/cursos/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListCurso() throws Exception {
	  		mockMvc.perform(get("/api/cursos")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveCurso() throws Exception {
	   		mockMvc.perform(get("/api/cursos/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		//FACULTAD
		@Test
	   	void testShowNegativeFacultad() throws Exception {
	   		mockMvc.perform(get("/api/facultades/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListFacultad() throws Exception {
	  		mockMvc.perform(get("/api/facultades")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveFacultad() throws Exception {
	   		mockMvc.perform(get("/api/facultades/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		//DBFILE
		@Test
	   	void testShowNegativeDBFile() throws Exception {
	   		mockMvc.perform(get("/api/files/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
	/*	@Test
	   	void testShowPositiveDBFile() throws Exception {
	   		mockMvc.perform(get("/api/files/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}*/
		//FORO
		@Test
	   	void testShowNegativeForo() throws Exception {
	   		mockMvc.perform(get("/api/foros/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListForo() throws Exception {
	  		mockMvc.perform(get("/api/foros")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveForo() throws Exception {
	   		mockMvc.perform(get("/api/foros/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		
		//GRADO
		@Test
	   	void testShowNegativeGrado() throws Exception {
	   		mockMvc.perform(get("/api/grados/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListGrado() throws Exception {
	  		mockMvc.perform(get("/api/grados")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveGrado() throws Exception {
	   		mockMvc.perform(get("/api/grados/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		//HORARIO
		@Test
	   	void testShowNegativeHorario() throws Exception {
	   		mockMvc.perform(get("/api/horarios/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListHorario() throws Exception {
	  		mockMvc.perform(get("/api/horarios")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveHorario() throws Exception {
	   		mockMvc.perform(get("/api/horarios/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
		
		//PROFESOR
		@Test
	   	void testShowNegativeProfesor() throws Exception {
	   		mockMvc.perform(get("/api/profesores/{id}",TEST_ID_NEGATIVE))
	   		.andExpect(status().is(404));
	   	}
	 	
		@Test
	  	void testListProfesor() throws Exception {
	  		mockMvc.perform(get("/api/profesores")).andExpect(status().isOk()).
	  		andExpect(status().is2xxSuccessful());
		  }

	
		@Test
	   	void testShowPositiveProfesor() throws Exception {
	   		mockMvc.perform(get("/api/profesores/{id}",TEST_ID_POSITIVE))
	   		.andExpect(status().is2xxSuccessful());
	   	}
}