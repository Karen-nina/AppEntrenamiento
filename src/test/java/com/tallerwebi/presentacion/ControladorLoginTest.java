package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosIncompletosLogin;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ControladorLoginTest {

	private ControladorLogin controladorLogin;
	private Usuario usuarioMock;
	private DatosLogin datosLoginMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioLogin servicioLoginMock;
	private RepositorioUsuario repositorioUsuarioMock;
	private ServicioRutina repositorioRutinaSemanalMock;

	private ServicioTipoEntrenamiento servicioTipoEntrenamiento;
	@BeforeEach
	public void init(){
		datosLoginMock = new DatosLogin("dami@unlam.com", "123");
		usuarioMock = mock(Usuario.class);
		when(usuarioMock.getEmail()).thenReturn("dami@unlam.com");
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioLoginMock = mock(ServicioLogin.class);
		servicioTipoEntrenamiento = mock(ServicioTipoEntrenamiento.class);
		repositorioRutinaSemanalMock = mock(ServicioRutina.class);
		controladorLogin = new ControladorLogin(servicioLoginMock, repositorioUsuarioMock,repositorioRutinaSemanalMock,servicioTipoEntrenamiento);
	}

	@Test
	public void loginConUsuarioPasswordInorrectosDeberiaLlevarALoginNuevamente(){
		// preparacion
		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(null);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Usuario o clave incorrecta"));
		verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");
	}
	
	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
		// preparacion
		Usuario usuarioEncontradoMock = mock(Usuario.class);
		when(usuarioEncontradoMock.getRol()).thenReturn("ADMIN");

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(usuarioEncontradoMock);
		
		// ejecucion
		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/home"));
		verify(sessionMock, times(1)).setAttribute("ROL", usuarioEncontradoMock.getRol());
	}

	@Test
	public void registrameSiUsuarioNoExisteDeberiaCrearUsuarioYLlevarmeAFormularioAptitudFisica() throws UsuarioExistente {
		doNothing().when(servicioLoginMock).registrar(usuarioMock);

		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("formularioAptitudFisica"));
		verify(servicioLoginMock, times(1)).registrar(usuarioMock);
	}

	@Test
	public void registrarmeSiUsuarioExisteDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
		// preparacion
		doThrow(UsuarioExistente.class).when(servicioLoginMock).registrar(usuarioMock);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("nuevo-usuario"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("El usuario ya existe"));
	}

	@Test
	public void errorEnRegistrarmeDeberiaVolverAFormularioYMostrarErrorSiFaltanDatos() throws UsuarioExistente {
		// preparacion
		doThrow(DatosIncompletosLogin.class).when(servicioLoginMock).registrar(usuarioMock);

		// ejecucion


		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("nuevo-usuario"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Faltan datos para Registrar / Asegurate de elegir tu lugar de residencia"));
	}
	@Test
	public void queSeanObligatoriosLaUbicacionDelUsuarioAlRegistrarse(){
		givenNoHayUsuario();
		Usuario usuario = new Usuario();
		usuario.setPassword("12345678");
		usuario.setEmail("wal@wal.com");
		usuario.setLatitud(-4.222);
		usuario.setLongitud(-4.222);
		ModelAndView mav = whenElUsuarioSeRegistra(usuario);
		thenEnviaUbicacionYSeRegistraExitosamente(mav);
	}

	private void givenNoHayUsuario() {
	}
	private ModelAndView whenElUsuarioSeRegistra(Usuario usuario) {
		return controladorLogin.registrarme(usuario);
	}
	private void thenEnviaUbicacionYSeRegistraExitosamente(ModelAndView mav) {
		assertThat(mav.getViewName(), equalToIgnoringCase("formularioAptitudFisica"));
	}
}
