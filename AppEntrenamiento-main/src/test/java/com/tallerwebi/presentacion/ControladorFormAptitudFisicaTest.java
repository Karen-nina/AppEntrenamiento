package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;

public class ControladorFormAptitudFisicaTest {

    private ControladorAptitudFisica controladorFormulario;
    private ControladorLogin controladorLogin;
    private ServicioLogin servicioLoginMock;

    @Mock
    private ServicioRutina servicioRutinaMock;
    private RepositorioUsuario repositorioUsuarioMock;

    private ServicioTipoEntrenamiento servicioTipoEntrenamiento;

    @BeforeEach
    public void init(){
        servicioLoginMock = mock(ServicioLogin.class);
        repositorioUsuarioMock = mock(RepositorioUsuario.class);
        servicioTipoEntrenamiento = mock(ServicioTipoEntrenamiento.class);
        controladorLogin = new ControladorLogin(servicioLoginMock, repositorioUsuarioMock,servicioRutinaMock,servicioTipoEntrenamiento);
    }
    @Test
    public void queLuegoDeRegistrarmeMeLLeveAlFormularioDeAptitudFisica(){
        givenUsuarioRegistrado();
        Usuario usuario = new Usuario ();
        usuario.setEmail("nico@nico.com");
        usuario.setPassword("12345678");
        ModelAndView mav = whenMeRegistro(usuario);
        thenVistaFormularioExitosa(mav);
    }

    private void givenUsuarioRegistrado() {
    }
    private ModelAndView whenMeRegistro(Usuario usuario) {
     return  controladorLogin.registrarme(usuario);
    }
    private void thenVistaFormularioExitosa(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("formularioAptitudFisica"));
    }
}
