package com.tallerwebi.presentacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.NoHayArticulosDeEseTipo;
import com.tallerwebi.dominio.excepcion.NoHayInformacionDelArticulo;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ControladorTipsYNutricionTest {

    private ControladorTipsYNutricion controladorTipsYNutricion;
    private ServicioLogin servicioLoginMock;
    private ServicioTipsYNutricion servicioTipsYNutricionMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private Usuario usuarioEncontradoMock;
    private AptitudFisica aptitudFisicaMock;
    private List<Articulo> mockArticulos;
    private ServicioMembresia servicioMembresiaMock;

    @BeforeEach
    void init() {
        servicioLoginMock = mock(ServicioLogin.class);
        servicioTipsYNutricionMock = mock(ServicioTipsYNutricion.class);
        servicioMembresiaMock = mock(ServicioMembresia.class);
        controladorTipsYNutricion = new ControladorTipsYNutricion(servicioLoginMock, servicioTipsYNutricionMock,servicioMembresiaMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        usuarioEncontradoMock = mock(Usuario.class);
        aptitudFisicaMock = mock(AptitudFisica.class);
        mockArticulos = mock(List.class);

        when(requestMock.getSession(false)).thenReturn(sessionMock);
        when(sessionMock.getAttribute("Email")).thenReturn("test@example.com");
    }

    private void givenUsuarioExistenteYExistenRegistros() throws UsuarioInexistenteException, NoHayArticulosDeEseTipo {
        List<TipoEntrenamiento> entrenamientosLis = new ArrayList<TipoEntrenamiento>();
        TipoEntrenamiento entrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        entrenamientosLis.add(entrenamiento);
        when(usuarioEncontradoMock.getAptitudFisica()).thenReturn(aptitudFisicaMock);
        when(aptitudFisicaMock.getTiposEntrenamiento()).thenReturn(entrenamientosLis);
        when(servicioLoginMock.buscarPorMail(anyString())).thenReturn(usuarioEncontradoMock);
        when(servicioTipsYNutricionMock.buscarTipsPorTipoDeEntrenamiento("Musculacion",0,10)).thenReturn(mockArticulos);
    }

    private void givenUsuarioExistenteYNoExistenRegistros() throws UsuarioInexistenteException, NoHayArticulosDeEseTipo {
        List<TipoEntrenamiento> entrenamientosLis = new ArrayList<>();
        TipoEntrenamiento entrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        entrenamientosLis.add(entrenamiento);
        when(usuarioEncontradoMock.getAptitudFisica()).thenReturn(aptitudFisicaMock);
        when(aptitudFisicaMock.getTiposEntrenamiento()).thenReturn(entrenamientosLis);
        when(servicioLoginMock.buscarPorMail(anyString())).thenReturn(usuarioEncontradoMock);
        when(servicioTipsYNutricionMock.buscarTipsPorTipoDeEntrenamiento(any(), anyInt(), anyInt()))
                .thenThrow(new NoHayArticulosDeEseTipo());
    }

    @Test
    public void queExistaLaVistaDeTipsYNutricionSiEstaLogeado() throws UsuarioInexistenteException, NoHayArticulosDeEseTipo {
        givenUsuarioExistenteYExistenRegistros();
        ModelAndView mav = whenIngresaASeccionTipsYNutricion();
        thenVistaYComponentesExitoso(mav);
    }

    private void thenVistaYComponentesExitoso(ModelAndView mav) {
        assertEquals("tipsYNutricion", mav.getViewName());
    }

    private ModelAndView whenIngresaASeccionTipsYNutricion() throws UsuarioInexistenteException, NoHayArticulosDeEseTipo {
        return controladorTipsYNutricion.vistaTipsYNutricion("todos",0,10, requestMock);
    }

    @Test
    public void queSeEnvieUnMensajeDeErrorSiNoHayTipsDisponibles() throws NoHayArticulosDeEseTipo, UsuarioInexistenteException {
        givenUsuarioExistenteYNoExistenRegistros();
        ModelAndView mav = whenIngresaASeccionTipsYNutricion();
        thenVistaSinArticuloYConError(mav);
    }

    private void thenVistaSinArticuloYConError(ModelAndView mav) {
        assertThat(mav.getModel().get("error").toString(), equalToIgnoringCase("No hay articulos"));
    }

    @Test
    public void obtenerUnArticulo() throws NoHayInformacionDelArticulo {
        Long id = 1L;
        Articulo articuloMock = new Articulo();
        when(servicioTipsYNutricionMock.getArticuloPorId(id)).thenReturn(articuloMock);
        Articulo resultado = whenObtengoElArticulo(id);
        thenHayUnRegistro(resultado, articuloMock,id);

    }

    private void thenHayUnRegistro(Articulo resultado, Articulo articuloMock, Long id) throws NoHayInformacionDelArticulo {
        assertNotNull(resultado);
        assertEquals(articuloMock, resultado);
        verify(servicioTipsYNutricionMock, times(1)).getArticuloPorId(id);
    }

    private Articulo whenObtengoElArticulo(Long id) throws NoHayInformacionDelArticulo {
       return  controladorTipsYNutricion.obtenerDetalleArticulo(id);
    }
}
