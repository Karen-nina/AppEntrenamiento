package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ControladorMapaTests {
    private HttpServletRequest requestMock;
    private MapaController controller;
    private HttpSession sessionMock;

    private ServicioMapa servicioSearchMock;
    private ServicioMembresia servicioMembresiaMock;
    private ServicioUsuario servicioUsuarioMock;
    private Membresia membresiaMock;
    private Usuario usuarioMock;

    @BeforeEach
    public void setUp() {
        servicioSearchMock = mock(ServicioMapa.class);
        servicioMembresiaMock = mock(ServicioMembresia.class);
        servicioUsuarioMock = mock(ServicioUsuario.class);
        controller = new MapaController(servicioSearchMock, servicioMembresiaMock, servicioUsuarioMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        membresiaMock = mock(Membresia.class);
        usuarioMock = mock(Usuario.class);
        when(requestMock.getSession(false)).thenReturn(sessionMock);
        when(sessionMock.getAttribute("Email")).thenReturn("usuario@example.com"); // Email válido en la sesión
        when(sessionMock.getAttribute("id")).thenReturn(1L); // ID válido en la sesión
        when(servicioUsuarioMock.buscarPorId(1L)).thenReturn(usuarioMock); // Usuario válido
        when(usuarioMock.getId()).thenReturn(1L); // ID del usuario
        when(usuarioMock.getLatitud()).thenReturn(-34.74973643128108); // Latitud del usuario
        when(usuarioMock.getLongitud()).thenReturn(-58.571734784656066); // Longitud del usuario
        when(servicioMembresiaMock.membresiasPorId(1L)).thenReturn(membresiaMock); // Membresía válida
        when(membresiaMock.getTipo()).thenReturn("PREMIUM"); // Tipo de membresía que permite acceso
    }

    @Test
    public void cuandoNoSeEncuentranLugaresDeberiaLanzarError() throws Exception {
        // Configurar el comportamiento del mock cuando el servicio busca lugares y no encuentra ninguno
        when(servicioSearchMock.buscarSitios(-34.74973643128108, -58.571734784656066)).thenReturn(Collections.emptyList());

        // Ejecutar el método bajo prueba
        ModelAndView mav = controller.irASearch(null, null, requestMock);

        // Verificar el resultado esperado
        assertEquals("mapaBuscador", mav.getViewName());
        assertEquals("No se encontraron lugares para los filtros seleccionados.", mav.getModel().get("error"));
    }

    @Test
    public void cuandoUsuarioNoTieneMembresiaAccesoDenegado() throws Exception {
        // Configurar el comportamiento del mock para una membresía gratuita
        when(membresiaMock.getTipo()).thenReturn("GRATUITO");

        // Ejecutar el método bajo prueba
        ModelAndView mav = controller.irASearch(null, null, requestMock);

        // Verificar el resultado esperado
        assertEquals("datos", mav.getViewName()); // Debe redirigir a "datos" por falta de acceso
        assertEquals("No tienes acceso a esta sección. Actualice su Membresia", mav.getModel().get("error"));
    }

    @Test
    public void actualizarUbicacionUsuarioConExito() throws Exception {
        when(sessionMock.getAttribute("id")).thenReturn(1L);
        when(servicioUsuarioMock.buscarPorId(1L)).thenReturn(usuarioMock);

        ResponseEntity<String> response = controller.actualizarUbicacion(-34.700000, -58.500000, sessionMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ubicación actualizada exitosamente.", response.getBody());
    }

    @Test
    public void actualizarUbicacionUsuarioErrorNoAutenticado() {
        when(sessionMock.getAttribute("id")).thenReturn(null);

        ResponseEntity<String> response = controller.actualizarUbicacion(-34.700000, -58.500000, sessionMock);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Usuario no autenticado", response.getBody());
    }

    @Test
    public void cuandoSeFiltraPorTipoDeActividadDeberiaDevolverLugaresFiltrados() throws Exception {
        // Configurar los mocks para devolver una lista de lugares
        List<Lugar> lugares = new ArrayList<>();
        Lugar lugar1 = new Lugar("Lugar 1", "Ubicación 1", -58.571734784656066, -34.74973643128108);
        lugares.add(lugar1);

        when(servicioSearchMock.buscarLugaresPorTipoActividad(1L, -34.74973643128108, -58.571734784656066)).thenReturn(lugares);

        // Ejecutar el método bajo prueba
        ModelAndView mav = controller.irASearch("1", null, requestMock);

        // Verificar el resultado esperado
        assertEquals("mapaBuscador", mav.getViewName());
        assertEquals(lugares, mav.getModel().get("lugares"));
    }

    @Test
    public void cuandoSeFiltraPorDistanciaDeberiaDevolverLugaresFiltrados() throws Exception {
        // Configurar los mocks para devolver una lista de lugares
        List<Lugar> lugares = new ArrayList<>();
        Lugar lugar1 = new Lugar("Lugar 1", "Ubicación 1", -58.571734784656066, -34.74973643128108);
        Lugar lugar2 = new Lugar("Lugar 2", "Ubicación 2", -58.571734784656066, -34.74973643128108);
        lugares.add(lugar1);
        lugares.add(lugar2);

        when(servicioSearchMock.buscarSitios(-34.74973643128108, -58.571734784656066)).thenReturn(lugares);
        when(servicioSearchMock.filtrarLugaresPorDistancia(lugares, -34.74973643128108, -58.571734784656066, 3.0)).thenReturn(Collections.singletonList(lugar1));

        // Ejecutar el método bajo prueba
        ModelAndView mav = controller.irASearch(null, 3.0, requestMock);

        // Verificar el resultado esperado
        assertEquals("mapaBuscador", mav.getViewName());
        assertEquals(Collections.singletonList(lugar1), mav.getModel().get("lugares"));
    }

    @Test
    public void cuandoSeFiltraPorTipoDeActividadYDistanciaDeberiaDevolverLugaresFiltrados() throws Exception {
        // Configurar los mocks para devolver una lista de lugares
        List<Lugar> lugares = new ArrayList<>();
        Lugar lugar1 = new Lugar("Lugar 1", "Ubicación 1", -58.571734784656066, -34.74973643128108);
        Lugar lugar2 = new Lugar("Lugar 2", "Ubicación 2", -58.571734784656066, -34.74973643128108);
        lugares.add(lugar1);
        lugares.add(lugar2);

        when(servicioSearchMock.buscarLugaresPorTipoActividad(1L, -34.74973643128108, -58.571734784656066)).thenReturn(lugares);
        when(servicioSearchMock.filtrarLugaresPorDistancia(lugares, -34.74973643128108, -58.571734784656066, 3.0)).thenReturn(Collections.singletonList(lugar1));

        // Ejecutar el método bajo prueba
        ModelAndView mav = controller.irASearch("1", 3.0, requestMock);

        // Verificar el resultado esperado
        assertEquals("mapaBuscador", mav.getViewName());
        assertEquals(Collections.singletonList(lugar1), mav.getModel().get("lugares"));
    }
}