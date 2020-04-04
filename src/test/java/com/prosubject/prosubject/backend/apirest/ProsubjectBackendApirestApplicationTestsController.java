package com.prosubject.prosubject.backend.apirest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.prosubject.prosubject.backend.apirest.controller.AdministradorController;
import com.prosubject.prosubject.backend.apirest.model.Administrador;
import com.prosubject.prosubject.backend.apirest.model.Authority;
import com.prosubject.prosubject.backend.apirest.model.UserAccount;
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
import com.prosubject.prosubject.backend.apirest.service.UniversidadService;
import com.prosubject.prosubject.backend.apirest.service.UserAccountService;
import com.prosubject.prosubject.backend.apirest.service.ValoracionService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest 
@RunWith(SpringRunner.class)
class ProsubjectBackendApirestApplicationTestsController {
	

	private static final Long TEST_ADMINISTRADOR_ID = 300L;


	private static final Long TEST_USERACCOUNT_ID = 300L;


	private static final Object TEST_UNIVERSIDAD_ID = 300L;


	//MOCKITO
	@Autowired
	private AdministradorController AdministradorController;
	@MockBean
	private AdministradorService administradorService;
	
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
	
	@Autowired
	private MockMvc mockMvc;


	// Administrador
	//controlador
	@BeforeEach	
	void setup() {

		administrador = new Administrador();
		userAccount = new UserAccount();
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
		given(this.administradorService.findOne(TEST_ADMINISTRADOR_ID)).willReturn(administrador);
		given(this.userAccountService.findByUserAndPass(userAccount.getUsername(), userAccount.getPassword())).willReturn(userAccount);
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
	   		.andExpect(status().is2xxSuccessful())
	   		.andDo(MockMvcResultHandlers.print());
	   	}
	   	
	   	@Test
	   	void testShowNegativeUniversidad() throws Exception {
	   		mockMvc.perform(get("/api/universidades/{id}",TEST_UNIVERSIDAD_ID))
	   		.andExpect(status().is(404));
	   	}
	

	  
}