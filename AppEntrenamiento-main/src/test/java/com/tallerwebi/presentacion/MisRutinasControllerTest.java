package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosMalIngresadosException;
import com.tallerwebi.dominio.excepcion.esMenorDeEdadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MisRutinasControllerTest {

    private MisRutinasController misRutinasController;
    private ServicioRutina servicioRutinaMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private RepositorioRutina repositorioRutinaMock;

    @BeforeEach
    public void init() {
        servicioRutinaMock = mock(ServicioRutina.class);
        misRutinasController = new MisRutinasController(servicioRutinaMock, repositorioRutinaMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
    }

    @Test
    public void queMuestreElResumenDeRutinaConUsuarioAutenticado(){
        // Configurar el mock de HttpSession para simular la sesión iniciada
        when(sessionMock.getAttribute("Email")).thenReturn("usuario@example.com");

        // Configurar el mock de HttpServletRequest para devolver el HttpSession simulado
        when(requestMock.getSession(false)).thenReturn(sessionMock);

        // Llamar a tu método de prueba y obtener el ModelAndView
        ModelAndView mav = whenVerMisRutinas(requestMock);

        // Verificar si la vista devuelta es la esperada
        thenVistaResumenRutinaExitosa(mav);
    }

    @Test
    public void queRedirijaALoginSiUsuarioNoAutenticado(){
        // Configurar el mock de HttpServletRequest para devolver null, indicando que no hay sesión
        when(requestMock.getSession(false)).thenReturn(null);

        // Llamar a tu método de prueba y obtener el ModelAndView
        ModelAndView mav = whenVerMisRutinas(requestMock);

        // Verificar si la vista devuelta es la esperada (redirección a login)
        thenRedireccionALogin(mav);
    }

    private ModelAndView whenVerMisRutinas(HttpServletRequest requestMock) {
        // Aquí deberías llamar al método del controlador que estás probando
        // y pasarle el HttpServletRequest que has configurado
        return misRutinasController.verMisRutinas(requestMock);
    }

    private void thenVistaResumenRutinaExitosa(ModelAndView mav) {
        // Verificar si la vista devuelta es la esperada (misRutinas)
        assertThat(mav.getViewName(), equalToIgnoringCase("misRutinas"));
    }

    private void thenRedireccionALogin(ModelAndView mav) {
        // Verificar si la vista devuelta es una redirección a login
        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }
}

