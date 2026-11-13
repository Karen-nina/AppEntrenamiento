package com.tallerwebi.presentacion;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ControladorMembresiaTest {

    private ServicioLogin servicioLoginMock;
    private ServicioMembresia servicioMembresiaMock;
    private ServicioRutina servicioRutinaMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ControladorMembresia controladorMembresia;
    private ServicioMercadoPago servicioMercadoPagoMock;
    @BeforeEach
    public void init() {
        servicioLoginMock = mock(ServicioLogin.class);
        servicioMembresiaMock = mock(ServicioMembresia.class);
        servicioRutinaMock = mock(ServicioRutina.class);
        sessionMock = mock(HttpSession.class);
        requestMock = mock(HttpServletRequest.class);
        servicioMercadoPagoMock = mock(ServicioMercadoPago.class);
        controladorMembresia = new ControladorMembresia(servicioLoginMock,servicioMembresiaMock,servicioRutinaMock,servicioMercadoPagoMock,sessionMock); // Instancia real del controlador

        when(requestMock.getSession(false)).thenReturn(sessionMock);
        when(sessionMock.getAttribute("Email")).thenReturn("test@example.com");
    }

    @Test
    public void testAsignarMembresiaUsuarioExistente() throws UsuarioInexistenteException, MPException, MPApiException {
        // Arrange
        String email = "usuario@example.com";
        String tipo = "Premium";
        int duracion = 6;
        List<RutinaSemanal> rutinaSemanal = new ArrayList<>();
        Usuario usuario=  givenUsuarioCreado(email);
        ModelAndView mav = whenSeAsignaMembresia(email,usuario,tipo,duracion,rutinaSemanal);
        thenRedirijoAlHome(mav,usuario,rutinaSemanal);
    }

    private ModelAndView whenSeAsignaMembresia(String email, Usuario usuario, String tipo,int duracion,List<RutinaSemanal> rutinaSemanal) throws UsuarioInexistenteException, MPException, MPApiException {
        when(servicioLoginMock.buscarPorMail(email)).thenReturn(usuario);
        Membresia membresiaMock;
        membresiaMock = mock(Membresia.class);
        doNothing().when(servicioMembresiaMock).crearMembresia(membresiaMock);
        //mockeo mercadopago
        DatosPreferencia preferenceMock = mock(DatosPreferencia.class);

        preferenceMock.urlCheckout = "http://localhost:8080/validar-pago";
        when(servicioMercadoPagoMock.crearPreferenciaPago(anyDouble())).thenReturn(preferenceMock);
        return controladorMembresia.asignarMembresia(tipo, email, duracion, requestMock);
    }
    public void thenRedirijoAlHome(ModelAndView mav, Usuario usuario, List<RutinaSemanal> rutinaSemanal) throws MPException, MPApiException {

        ModelMap modelo = mav.getModelMap();
        assertNotNull(mav);
        verify(servicioMercadoPagoMock, times(1)).crearPreferenciaPago(anyDouble());
        assertEquals("redirect:http://localhost:8080/validar-pago", mav.getViewName());
    }
    private Usuario givenUsuarioCreado(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        return usuario;
    }

    @Test
    public void testAsignarMembresiaUsuarioInexistente() throws UsuarioInexistenteException {
        // Arrange
        String email = "usuario_inexistente@example.com";
        String tipo = "Premium";
        int duracion = 6;


        when(servicioLoginMock.buscarPorMail(email)).thenThrow(new UsuarioInexistenteException("Usuario no encontrado"));
        Usuario usuario=  givenUsuarioCreado(email);
        ModelAndView mav = controladorMembresia.asignarMembresia(tipo, email, duracion, requestMock);
        thenDevuelvoErrorNoQueNoExisteUsuarioRegistrado(mav);
    }
    public void   thenDevuelvoErrorNoQueNoExisteUsuarioRegistrado(ModelAndView mav){
        System.out.println(mav);
        ModelMap modelo = mav.getModelMap();
        assertNotNull(mav);
        assertEquals("home", mav.getViewName());
        assertEquals("Usuario no encontrado", modelo.get("error"));
    }
    @Test
    public void testValidarPagoExitoso() throws MembresiaNoEncontrada {
        //Preparacion
        String status = "approved";
        Long membresiaId = 1L;
        Membresia membresiaMock = new Membresia();
        membresiaMock.setId(membresiaId);
        Usuario usuarioMock = new Usuario();
        List<RutinaSemanal> rutinaSemanalMock = new ArrayList<>();

        when(sessionMock.getAttribute("idMembresia")).thenReturn(membresiaId);
        when(servicioMembresiaMock.buscarPorId(membresiaId)).thenReturn(membresiaMock);
        when(servicioRutinaMock.generarRutinaSemanal(usuarioMock)).thenReturn(rutinaSemanalMock);

        // ejecucion
        ModelAndView mav = controladorMembresia.validarPago(status);

        // validacion
        assertEquals("redirect:/home", mav.getViewName());
        verify(sessionMock).setAttribute("membresia", membresiaMock);
        verify(sessionMock).removeAttribute("idMembresia");
        verify(sessionMock).setAttribute("rutinaSemanal", rutinaSemanalMock);
        verify(servicioMembresiaMock, times(1)).buscarPorId(membresiaId);

    }

    @Test
    public void testValidarPagoError() throws MembresiaNoEncontrada {
        //Preparacion
        String status = "rejected";
        Long membresiaId = 1L;

        when(sessionMock.getAttribute("idMembresia")).thenReturn(membresiaId);

        // Ejecucion
        ModelAndView mav = controladorMembresia.validarPago(status);

        // Validacion.
        assertEquals("redirect:/home", mav.getViewName());
        verify(servicioMembresiaMock).eliminarPorId(membresiaId);
        assertEquals("Error al procesar el pago.", mav.getModel().get("error"));
    }
}
