package com.prosubject.prosubject.backend.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;


import java.util.HashMap;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prosubject.prosubject.backend.apirest.controller.AdministradorController;
import com.prosubject.prosubject.backend.apirest.controller.AlumnoController;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Asignatura;
import com.prosubject.prosubject.backend.apirest.model.Authority;
import com.prosubject.prosubject.backend.apirest.model.Carrito;
import com.prosubject.prosubject.backend.apirest.model.City;
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
import com.prosubject.prosubject.backend.apirest.model.Valoracion;
import com.prosubject.prosubject.backend.apirest.repository.AdministradorRepository;
import com.prosubject.prosubject.backend.apirest.repository.AlumnoRepository;
import com.prosubject.prosubject.backend.apirest.repository.AsignaturaRepository;
import com.prosubject.prosubject.backend.apirest.repository.EspacioRepository;
import com.prosubject.prosubject.backend.apirest.service.AdministradorService;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.AsignaturaService;
import com.prosubject.prosubject.backend.apirest.service.CarritoService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.ForoService;
import com.prosubject.prosubject.backend.apirest.service.HorarioService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;
import com.prosubject.prosubject.backend.apirest.service.ValoracionService;

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

	@Autowired
	private EspacioService espacioServicio;
	
	@Autowired
	private ValoracionService valoracionService;

	@Autowired
	private ForoService foroService;
	
	@Autowired
	private HorarioService horarioService;
	
	@Autowired
	private AsignaturaService asignaturaService;
	
	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private CarritoService	carritoService;
	
	@Autowired
	private AdministradorService	administradorService;

	@Autowired
	private AlumnoService	alumnoService;
	
	

	@Test
	public void adminControllerTest() {
		assertThat(adminController).isNotNull();
	}

	// Administrador
	@Test
	public void findAdministradorByUserAccountIdTest() {
		String nombre = "Antonio";
		Long id = (long) 1;
		Administrador a = adminRep.findAdministradorByUserAccountId(id);
		assertEquals(a.getNombre(), nombre);

	}

	// Probando sus getters and setters
	@Test
	public void GetterAndSetterAdministradorTest() {

		Administrador administrador = new Administrador();
		UserAccount useraccount = new UserAccount();
		administrador.setApellido1("Romero");
		administrador.setApellido2("Caceres");
		administrador.setNombre("Ana");
		administrador.setDni("47546251F");
		administrador.setTelefono("955792206");
		administrador.setUserAccount(useraccount);
		administrador.setEmail("anaromcac@alum.us.es");
		assertEquals(administrador.getApellido1(), "Romero");
		assertEquals(administrador.getApellido2(), "Caceres");
		assertEquals(administrador.getNombre(), "Ana");
		assertEquals(administrador.getDni(), "47546251F");
		assertEquals(administrador.getTelefono(), "955792206");
		assertEquals(administrador.getUserAccount(), useraccount);
		assertEquals(administrador.getEmail(), "anaromcac@alum.us.es");

	}
	@Test
	public void ServicioAdministradorFindAll() throws Exception {
		List<Administrador> res = administradorService.findAll();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAdministradorFindOne() throws Exception {
		Administrador res = administradorService.findOne(1l);
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioAdministradorFindUserAccount() throws Exception {
		Administrador res = administradorService.findByUserAccount(1l);
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioAdministradoremailsAdministradores() throws Exception {
		List<String> res = administradorService.emailsAdministradores();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAdministradordnisAdministradores() throws Exception {
		List<String> res = administradorService.dnisAdministradores();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAdministradorEdit() throws Exception {
		Administrador admin = administradorService.findOne(1l);
		Administrador res= this.administradorService.edit(1l, admin);
		assertThat(res).isNotNull();
	}

	// Alumno
	@Test
	public void alumControllerTest() {
		assertThat(alumController).isNotNull();
	}

	@Test
	public void GetterAndSetterAlumnosTest() {

		Alumno alumno = new Alumno();
		UserAccount useraccount = new UserAccount();
		alumno.setNombre("Blas");
		alumno.setApellido1("Infante");
		alumno.setApellido2("Garcia");
		alumno.setDni("47546250Y");
		alumno.setEmail("blasinfantegarcia@gmail.com");
		alumno.setTelefono("603552740");
		alumno.setUserAccount(useraccount);
		assertEquals(alumno.getUserAccount(), useraccount);
		assertEquals(alumno.getNombre(), "Blas");
		assertEquals(alumno.getApellido1(), "Infante");
		assertEquals(alumno.getApellido2(), "Garcia");
		assertEquals(alumno.getDni(), "47546250Y");
		assertEquals(alumno.getEmail(), "blasinfantegarcia@gmail.com");
		assertEquals(alumno.getTelefono(), "603552740");

	}

	@Test
	public void findAlumnoByUserAccountIdTest() {
		String nombre = "Fernando";
		Long id = (long) 28;
		Alumno aux = alumRep.findAlumnoByUserAccountId(id);
		assertEquals(aux.getNombre(), nombre);
	}
	@Test
	public void ServicioAlumnoFindAll() throws Exception {
		List<Alumno> res = alumnoService.findAll();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAlumnoFindOne() throws Exception {
		Alumno res = alumnoService.findOne(1l);
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioAlumnoFindUserAccount() throws Exception {
		Alumno res = alumnoService.findByUserAccount(15l);
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioAlumnoFindByUsername() throws Exception {
		Alumno res = alumnoService.findByUsername("marioogonzalez");
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioAlumnoemailsAlumnos() throws Exception {
		List<String> res = alumnoService.emailsAlumno();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAlumnodnisAlumnoes() throws Exception {
		List<String> res = alumnoService.DNIsAlumno();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAlumnoEdit() throws Exception {
		Alumno alum = alumnoService.findOne(1l);
		Alumno res= this.alumnoService.edit(1l, alum);
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAlumnoDeUnHorario() throws Exception {
		List<Alumno> res = alumnoService.alumnosDeUnHorario(1l);
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAlumnoQueHanValoradoUnEspacio() throws Exception {
		List<Alumno> res = alumnoService.alumnosQueHanValoradoUnEspacio(1l);
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAlumnoDeUnEspacio() throws Exception {
		List<Alumno> res = alumnoService.alumnosDeUnEspacio(1l);
		assertThat(res).isNotNull();
	}

	// Asignatura
	@Test
	public void findListaAsignaturasTest() {
		String uni = "Universidad de Sevilla";
		String facultad = "Escuela Técnica Superior de Ingeniería Informática";
		String grado = "Ingeniería del Software";
		String curso = "PRIMERO";
		List<Asignatura> res = asigRep.findListaAsignaturas(uni, facultad, grado, curso);
		assertThat(res).isNotEmpty();
		assertEquals(res.get(0).getNombre(), "Fundamentos de programación");
	}

	@Test
	public void GetterAndSetterAsignaturasTest() {

		Asignatura asignatura = new Asignatura();
		asignatura.setNombre("Fundamentos de la programación II");
		assertEquals(asignatura.getNombre(), "Fundamentos de la programación II");

	}
	
	@Test
	public void ServicioAsignaturaFindAll() throws Exception {
		List<Asignatura> res = asignaturaService.findAll();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAsignaturaFindOne() throws Exception {
		Asignatura res = asignaturaService.findOne(1l);
		this.asignaturaService.save(res);
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAsignaturaPorNombre() throws Exception {
		Asignatura res = asignaturaService.getAsignaturaPorNombre("Fundamentos de programación");
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioAsignaturaListaAsignatura() throws Exception {
		List<Asignatura> res = asignaturaService.findListaAsignaturas("Universidad de Sevilla","Escuela Técnica Superior de Ingeniería Informática","Ingeniería del Software","PRIMERO");
		assertThat(res).isNotNull();
	}

	// Curso
	@Test
	public void GetterAndSetterCursoTest() {
		Curso curso = new Curso();
		curso.setNombre("PRIMERO");
		assertEquals(curso.getNombre(), "PRIMERO");

	}

	



	// Facultad

	@Test
	public void GetterAndSetterFacultadTest() {

		Facultad facultad = new Facultad();
		Universidad universidad = new Universidad();
		universidad.setNombre("Universidad de Cordoba");
		facultad.setNombre("Prueba facultad de Cordoba");
		facultad.setUniversidad(universidad);

		assertEquals(facultad.getNombre(), "Prueba facultad de Cordoba");
		assertEquals(facultad.getUniversidad(), universidad);
		assertEquals(universidad.getNombre(), "Universidad de Cordoba");

	}

	// Foro
	@Test
	public void GetterAndSetterForoTest() {

		Foro foro = new Foro();
		Date date = new Date();
		foro.setTitulo("Foro Fundamentos de programacion");
		foro.setFechaCreacion(date);
		assertEquals(foro.getTitulo(), "Foro Fundamentos de programacion");
		assertEquals(foro.getFechaCreacion(), date);

	}

	// Grado
	@Test
	public void GetterAndSetterGradoTest() {

		Grado grado = new Grado();
		Facultad facultad = new Facultad();
		grado.setNombre("Ingenieria del Software");
		grado.setNumerocursos(1);
		grado.setFacultad(facultad);
		assertEquals(grado.getNombre(), "Ingenieria del Software");
		assertEquals(grado.getNumerocursos(), 1L);
		assertEquals(grado.getFacultad(), facultad);

	}

	

	

	// Profesor
	@Test
	public void GetterAndSetterProfesorTest() {

		Profesor profesor = new Profesor();
		UserAccount userAccount = new UserAccount();
		DBFile expediente = new DBFile();

		profesor.setUserAccount(userAccount);
		profesor.setNombre("Rodrigo");
		profesor.setApellido1("Rojas");
		profesor.setApellido2("Gutierrez");
		profesor.setDni("47546231T");
		profesor.setEmail("rodrojgut@alum.us.es");
		profesor.setTelefono("123456789");
		profesor.setTarifaPremium(true);
		// profesor.setExpedienteValidado(true);
		profesor.setExpendiente(expediente);
		assertEquals(profesor.getUserAccount(), userAccount);
		assertEquals(profesor.getNombre(), "Rodrigo");
		assertEquals(profesor.getApellido1(), "Rojas");
		assertEquals(profesor.getApellido2(), "Gutierrez");
		assertEquals(profesor.getDni(), "47546231T");
		assertEquals(profesor.getEmail(), "rodrojgut@alum.us.es");
		assertEquals(profesor.getTelefono(), "123456789");
		assertEquals(profesor.getTarifaPremium(), true);
		// assertEquals(profesor.getExpedienteValidado(),true);
		assertEquals(profesor.getExpendiente(), expediente);

	}
	@Test
	public void ServicioProfesorFindAll() throws Exception {
		List<Profesor> res = profesorService.findAll();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioProfesorFindOne() throws Exception {
		Profesor res = profesorService.findOne(1l);
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioProfesorFindUserAccount() throws Exception {
		Profesor res = profesorService.findByUserAccount(14l);
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioProfesorFindByUsername() throws Exception {
		Profesor res = profesorService.findByUsername("marioogonzalez1");
		assertThat(res).isNotNull();
	}
	@Test
	public void ServicioProfesoremailsProfesors() throws Exception {
		List<String> res = profesorService.emailsProfesor();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioProfesordnisProfesores() throws Exception {
		List<String> res = profesorService.DNIsProfesor();
		assertThat(res).isNotNull();
	}
	

	
	@Test
	public void ServicioProfesorExpedientePendiete() throws Exception {
		List<Profesor> res = profesorService.profesoresExpedientePendiete();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServicioProfesorTarifaPremium() throws Exception {
		List<Profesor> res = profesorService.profesoresTarifaPremium();
		assertThat(res).isNotNull();
	}
	
	@Test
	public void ServiciProfesorvaloracionMedia() throws Exception {
		this.profesorService.valoracionMedia(1l);
	}

	

	// Rango
	@Test
	public void GetterAndSetterRangoTest() {

		Rango rango = new Rango();
		Horario horario = new Horario();
		Alumno alumno = new Alumno();

		Date fechaInicio = new Date();
		Date fechaFin = new Date();
		fechaFin.after(fechaInicio);
		rango.setId(300L);
		rango.setAlumno(alumno);
		rango.setHorario(horario);

		assertEquals(rango.getId(), 300L);
		assertEquals(rango.getAlumno(), alumno);
		assertEquals(rango.getHorario(), horario);
		

	}

	// Respuesta
	@Test
	public void GetterAndSetterRespuestaTest() {

		Respuesta respuesta = new Respuesta();
		UserAccount userAccount = new UserAccount();
		Foro foro = new Foro();

		respuesta.setUser(userAccount);
		respuesta.setForo(foro);
		respuesta.setContenido("Hola tengo una duda..");
		respuesta.setCreacionRespuesta(new Date());

		assertEquals(respuesta.getUser(), userAccount);
		assertEquals(respuesta.getForo(), foro);
		assertEquals(respuesta.getContenido(), "Hola tengo una duda..");
		assertEquals(respuesta.getCreacionRespuesta(), new Date());

	}

	// Universidad
	@Test
	public void GetterAndSetterUniversidadTest() {

		Universidad universidad = new Universidad();

		universidad.setNombre("Universidad Pablo de Olavide");
		assertEquals(universidad.getNombre(), "Universidad Pablo de Olavide");

	}

	// UserAccount
	@Test
	public void GetterAndSetterUserAccountTest() {

		UserAccount userAccount = new UserAccount();

		userAccount.setAutoridad(Authority.ALUMNO);
		userAccount.setPassword("darkme23123123");
		userAccount.setUsername("aurelio");

		assertEquals(userAccount.getAutoridad(), Authority.ALUMNO);
		assertEquals(userAccount.getPassword(), "darkme23123123");
		assertEquals(userAccount.getUsername(), "aurelio");

	}
	
	//Foro
	@Test
	public void ServicioForoFindOne() {
		Long id = new Long(1);
		Foro res = foroService.findOne(id);
		assertThat(res).isNotNull();

	}
	

	// Espacios
		@Test
		public void GetterAndSetterEspacioTest() {

			Asignatura asignatura = new Asignatura();
			Foro foro = new Foro();
			Profesor profesor = new Profesor();
			Espacio espacio = new Espacio();
			espacio.setAsignatura(asignatura);
			espacio.setForo(foro);
			espacio.setProfesor(profesor);
			espacio.setPrecio(12.0);
			espacio.setDraftMode(0);
			assertEquals(espacio.getAsignatura(), asignatura);
			assertEquals(espacio.getForo(), foro);
			assertEquals(espacio.getProfesor(), profesor);
			assertEquals(espacio.getPrecio(), 12.0);
			assertEquals(espacio.getDraftMode(), 0);
		}

		@Test
		public void findDisponiblesTest() {
			String uni = "Universidad de Sevilla";
			String facultad = "Escuela Técnica Superior de Ingeniería Informática";
			String grado = "Ingeniería del Software";
			String curso = "PRIMERO";
			String asignatura = "Fundamentos de programación";
			List<Espacio> res = espRep.findDisponibles(uni, facultad, grado, curso, asignatura);
			assertThat(res).isNotNull();

		}

		@Test
		public void ServicioEspaciofindDisponiblesTest() {
			String uni = "Universidad de Sevilla";
			String facultad = "Escuela Técnica Superior de Ingeniería Informática";
			String grado = "Ingeniería del Software";
			String curso = "PRIMERO";
			String asignatura = "Fundamentos de programación";
			List<Espacio> res = espacioServicio.findDisponibles(uni, facultad, grado, curso, asignatura);
			assertThat(res).isNotNull();

		}

	
		@Test
		public void ServicioEspacioFind() {
			Espacio espacio= this.espacioServicio.findOne(1L);
			assertThat(espacio.getId()).isEqualTo(1L);
		}

		


		@Test
		public void ServicioEspacioEspacioDeUnProfesor() {
			Long id = new Long(1);
			List<Espacio> res = espacioServicio.espaciosDeUnProfesor(id);
			assertThat(res).isNotNull();

		}

		@Test
		public void ServicioEspacioEspacioDeUnAlumno() {
			Long id = new Long(1);
			List<Espacio> res = espacioServicio.espaciosDeUnAlumno(id);
			assertThat(res).isNotNull();

		}

		@Test
		public void ServicioEspacioConHorarioConCapacidad() throws Exception {
			List<Espacio> res = espacioServicio.espaciosConHorarioConCapacidad();
			assertThat(res).isNotNull();

		}
		
		@Test
		public void ServicioEspaciosDeUnProfesorEnDraftMode() {
			Long id = new Long(3);
			List<Espacio> res = espacioServicio.espaciosDeUnProfesorEnDraftMode(id);
			assertThat(res).isNotNull();

		}


	// Horario
		@Test
		public void GetterAndSetterHorarioTest() {

			Horario horario = new Horario();
			Espacio espacio = new Espacio();
			Date date = new Date();
			Date date2 = new Date();
			date2.after(date);
			horario.setCapacidad(1L);
			horario.setDia(DiaSemana.Viernes);
			horario.setEspacio(espacio);
			horario.setHoraInicio(date);
			horario.setHoraFin(date2);

			assertEquals(horario.getCapacidad(), 1L);
			assertEquals(horario.getDia(), DiaSemana.Viernes);
			assertEquals(horario.getEspacio(), espacio);
			assertEquals(horario.getHoraInicio(), date);
			assertEquals(horario.getHoraFin(), date2);

		}
		
		
		@Test
		public void ServicioHorarioDisponiblesDeUnEspacio() throws Exception {
		 Long id = new Long(1);	
			List<Horario> res = horarioService.horariosDisponiblesDeUnEspacio(id);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioHorariosDeUnEspacio() throws Exception {
		 Long id = new Long(1);	
			List<Horario> res = horarioService.horariosDeUnEspacio(id);
			assertThat(res).isNotNull();
		}
		@Test
		public void ServicioHorariosDeAlumno() throws Exception {
		 Long id = new Long(1);	
			List<Horario> res = horarioService.horariosDeAlumno(id);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioHorariosDeProfesor() throws Exception {
		 Long id = new Long(1);	
			List<Horario> res = horarioService.horariosDeProfesor(id);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioHorariosNoEditablesDeUnProfesoro() throws Exception {
		 Long id = new Long(1);	
			List<Horario> res = horarioService.horariosNoEditablesDeUnProfesor(id);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioHorarioañadirAlumno() throws Exception {
		 Long horarioId = new Long(3);	
		 Long alumnoId = new Long(2);
			Horario res = horarioService.añadirAlumno(horarioId, alumnoId);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioHorarioFindOne() throws Exception {
			Horario res = horarioService.findOne(1);
			assertThat(res).isNotNull();
		}
		
		//Carrito
		@Test
		public void ServicioCarritoFindAll() throws Exception {
			List<Carrito> res = carritoService.findAll();
			assertThat(res).isNotNull();
		}
		@Test
		public void ServicioCarritoFindOne() throws Exception {
			Carrito res = carritoService.findOne(1l);
			assertThat(res).isNotNull();
		}
		@Test
		public void ServicioCarritoprecioMensualHorarios() throws Exception {
			Carrito res = carritoService.precioMensualHorarios(1l);
			assertThat(res).isNotNull();
		}
		@Test
		public void ServicioCarritosave() throws Exception {
			Carrito res = carritoService.findOne(1l);
			this.carritoService.save(res);
			assertThat(res).isNotNull();
		}
	
		
	/*	@Test
		public void ServicioHorarioSaveOne() throws Exception {

			Asignatura asignatura=this.asignaturaService.findOne(1L);
			Profesor profesor=this.profesorService.findOne(7L);
			Espacio espacio = new Espacio();
			espacio.setAsignatura(asignatura);
			espacio.setProfesor(profesor);
			espacio.setPrecio(12.0);
			espacio.setDraftMode(0);
			Horario horario = new Horario();
			Date date = new Date();
			Date date2 = new Date();
			date=this.horarioService.sumarRestarDiasFecha(date, 1);
			date2=this.horarioService.sumarRestarDiasFecha(date, 28);
			date2.after(date);
			horario.setCapacidad(1L);
			horario.setDia(DiaSemana.Viernes);
			horario.setEspacio(espacio);
			horario.setHoraInicio(date);
			horario.setHoraFin(date2);
			horario.setFechaInicio(date);

			
			Horario res = horarioService.saveOne(horario);
			//assertThat(res.);
		}
	*/
		
		
		
		//Valoracion
		@Test
		public void ServicioValoracionFindOne() throws Exception {
			Valoracion res = valoracionService.findOne(1l);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioValoracionPorEspacioId() throws Exception {
			List<Valoracion> res = valoracionService.valoracionesPorEspacioId(1l);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioValoracionMediaDeEspacio() throws Exception {
			Double res = valoracionService.valoracionMediaDeEspacio(1l);
			assertThat(res).isNotNull();
		}
		
		@Test
		public void ServicioValoracionMediaDeProfesor() throws Exception {
			Double res = valoracionService.valoracionMediaDeProfesor(1l);
			assertThat(res).isNotNull();
		
		}
		//CITY
		@Test
		public void GetterAndSetterCityTest() {

			City city = new City();
			City ciudad = new City(); 
			ciudad.setId(301L);
			city.setId(300L);
			city.setName("Sevilla");
			city.setPopulation(100000);
			city.toString();
			city.equals(ciudad);
			city.getId().equals(city.getId());
			city.hashCode();
			assertEquals(city.getId(), 300L);
			assertEquals(city.getName(), "Sevilla");
			assertEquals(city.getPopulation(), 100000);
			City city2 = new City("Malaga", 300000);
			assertEquals(city2.getName(), "Malaga");
			assertEquals(city2.getPopulation(), 300000);
			assertNotEquals(city.getName(), city2.getName());
			assertEquals(ciudad,ciudad);
			assertNotEquals(ciudad.getId(),city.getId());
			assertNotEquals(ciudad, null);
		}
		
	
	  //SELENIUM
	  @Test
	  public void testLoginProfesor() {
//		System.setProperty("webdriver.chrome.driver", "D:\\UNIVERSIDAD 2019-2020\\España\\4Curso\\ISPP\\sprint 1\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		
		driver.get("https://prosubject-v3.herokuapp.com/inicio");
	    driver.manage().window().setSize(new Dimension(1552, 840));
	    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
	    driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.name("username")).sendKeys("alejandrocano");
	    driver.findElement(By.name("password")).sendKeys("alejandrocano");
	    driver.findElement(By.cssSelector(".ng-dirty:nth-child(1)")).click();
	    driver.findElement(By.cssSelector(".btn")).click();
	    
	    driver.quit();
		
	  }
	  
	  //DBFILE
	  @Test
		public void GetterAndSetterDBFileTest() {
		  
		  DBFile file = new DBFile();
		  DBFile file2 = new DBFile("prueba","prueba",new byte[2]);
		  
		  file.setId(300L);
		  file.setFileType(".pdf");
		  file.setFileName("Notas");
		  file.setData(new byte[1]);
		  
		  assertEquals(file.getId(), 300L);
		  assertEquals(file.getFileType(), ".pdf");
		  assertEquals(file.getFileName(), "Notas");
		  assertEquals(file.getData(),file.getData());
		  assertNotEquals(file, file2);

		}
//		
//		
	  @Test
	  public void testLoginAlum() {
//		System.setProperty("webdriver.chrome.driver", "D:\\UNIVERSIDAD 2019-2020\\España\\4Curso\\ISPP\\sprint 1\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		    
		driver.get("https://prosubject-v3.herokuapp.com/inicio");
	    driver.manage().window().setSize(new Dimension(1146, 663));
	    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
	    driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.name("username")).sendKeys("anaromcac");
	    driver.findElement(By.name("password")).sendKeys("anaromcac");
	    driver.findElement(By.cssSelector(".btn")).click();
	    
	    driver.quit();
	  }
//
//	  @Test
//	  public void testRegistroAlumRepetido() {
//		System.setProperty("webdriver.chrome.driver", "D:\\UNIVERSIDAD 2019-2020\\España\\4Curso\\ISPP\\sprint 1\\chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();
//		  
//		driver.get("https://prosubject-v3.herokuapp.com/inicio");
//	    driver.manage().window().setSize(new Dimension(1146, 662));
//	    driver.findElement(By.cssSelector(".far")).click();
//	    driver.findElement(By.linkText("Registro")).click();
//	    driver.findElement(By.name("nombre")).click();
//	    driver.findElement(By.name("nombre")).sendKeys("Jesus");
//	    driver.findElement(By.name("apellido1")).sendKeys("Elias");
//	    driver.findElement(By.name("apellido2")).sendKeys("Rodriguez");
//	    driver.findElement(By.name("dni")).sendKeys("17842170N");
//	    driver.findElement(By.name("telefono")).click();
//	    driver.findElement(By.cssSelector("app-registro > .row")).click();
//	    driver.findElement(By.name("username")).sendKeys("jesu");
//	    driver.findElement(By.name("email")).click();
//	    driver.findElement(By.name("email")).sendKeys("jesuselias@gmail.com");
//	    driver.findElement(By.name("username")).click();
//	    driver.findElement(By.name("username")).sendKeys("jesuelia98");
//	    driver.findElement(By.cssSelector(".ng-invalid:nth-child(4) > .row:nth-child(1)")).click();
//	    driver.findElement(By.name("password")).sendKeys("jesuelia");
//	    driver.findElement(By.name("confirmPassword")).click();
//	    driver.findElement(By.name("confirmPassword")).sendKeys("jesuelia");
//	    driver.findElement(By.cssSelector("label:nth-child(6)")).click();
//	    driver.findElement(By.cssSelector(".btn")).click();
	    
//	  }
	  
//	  MisClasesAlum
//	  @Test
//	  public void testAlumMisClases() {
//		System.setProperty("webdriver.chrome.driver", "D:\\UNIVERSIDAD 2019-2020\\España\\4Curso\\ISPP\\sprint 1\\chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();
//		
//		driver.get("https://prosubject-v3.herokuapp.com/inicio");
//	    driver.manage().window().setSize(new Dimension(1154, 674));
//	    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
//	    driver.findElement(By.linkText("Login")).click();
//	    driver.findElement(By.name("username")).click();
//	    driver.findElement(By.name("username")).sendKeys("anaromcac");
//	    driver.findElement(By.cssSelector(".card-body")).click();
//	    driver.findElement(By.name("password")).click();
//	    driver.findElement(By.name("password")).sendKeys("anaromcac");
//	    driver.findElement(By.cssSelector(".btn")).click();
//	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	    driver.findElement(By.linkText("Mis clases")).click();
//			
//	  }
	  
	  //BusqAsigAlum
//	  @Test
//	  public void testAlumBusquedaAsig() {
//		System.setProperty("webdriver.chrome.driver", "D:\\UNIVERSIDAD 2019-2020\\España\\4Curso\\ISPP\\sprint 1\\chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();
//		
//		driver.get("https://prosubject-v3.herokuapp.com/inicio");
//	    driver.manage().window().setSize(new Dimension(1154, 674));
//	    driver.findElement(By.cssSelector(".far")).click();
//	    driver.findElement(By.linkText("Login")).click();
//	    driver.findElement(By.cssSelector(".form-group:nth-child(1)")).click();
//	    driver.findElement(By.name("username")).click();
//	    driver.findElement(By.name("username")).sendKeys("anaromcac");
//	    driver.findElement(By.cssSelector(".card-body")).click();
//	    driver.findElement(By.name("password")).click();
//	    driver.findElement(By.name("password")).sendKeys("anaromcac");
//	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	    driver.findElement(By.cssSelector(".btn")).click();
//	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	    driver.findElement(By.linkText("Busqueda de espacios")).click();
//	    {
//	      WebElement element = driver.findElement(By.linkText("Busqueda de espacios"));
//	      Actions builder = new Actions(driver);
//	      builder.moveToElement(element).perform();
//	    }
//	    {
//	      WebElement element = driver.findElement(By.tagName("body"));
//	      Actions builder = new Actions(driver);
//	      builder.moveToElement(element, 0, 0).perform();
//	    }
		
//		driver.get("https://prosubject-v3.herokuapp.com/inicio");
//		driver.manage().window().setSize(new Dimension(1146, 663));
//		driver.findElement(By.cssSelector(".dropdown-toggle")).click();
//		driver.findElement(By.linkText("Login")).click();
//		driver.findElement(By.name("username")).sendKeys("anaromcac");
//		driver.findElement(By.name("password")).sendKeys("anaromcac");
//		driver.findElement(By.cssSelector(".btn")).click();
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		driver.findElement(By.linkText("Busqueda de espacios")).click();
//		driver.findElement(By.xpath("/html/body/app-root/app-pages/div/app-header/nav/div/div/ul/li[2]/a")).click();
		
	    
//	    driver.findElement(By.xpath("/html/body/app-root/app-pages/div/div/app-busqueda-asignatura/div/div[1]/div/form/div[2]/div[2]/input")).sendKeys("PRIMERO");
		
//		driver.get("https://prosubject-v3.herokuapp.com/inicio");
//	    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
//	    driver.findElement(By.linkText("Login")).click();
//	    driver.findElement(By.name("username")).click();
//	    driver.findElement(By.name("username")).sendKeys("anaromcac");
//	    driver.findElement(By.cssSelector(".card-body")).click();
//	    driver.findElement(By.name("password")).click();
//	    driver.findElement(By.name("password")).sendKeys("anaromcac");
//	    driver.findElement(By.cssSelector(".btn")).click();
//	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	    driver.findElement(By.linkText("Busqueda de espacios")).click();
//	    driver.findElement(By.xpath("/html/body/app-root/app-pages/div/div/app-busqueda-asignatura/div/div[1]/div/form/div[2]/div[2]/input")).click();
//	    driver.findElement(By.xpath("/html/body/app-root/app-pages/div/div/app-busqueda-asignatura/div/div[1]/div/form/div[2]/div[2]/input")).sendKeys("PRIMERO");
//	    driver.findElement(By.cssSelector(".ng-invalid:nth-child(2)")).click();
//	    driver.findElement(By.cssSelector(".col-md-4:nth-child(3) > .form-control")).sendKeys("Fundamentos de programación");
//	    driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		
//	  }
	  
}
