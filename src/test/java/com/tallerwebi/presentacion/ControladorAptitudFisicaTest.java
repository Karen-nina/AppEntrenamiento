package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosMalIngresadosException;
import com.tallerwebi.dominio.excepcion.esMenorDeEdadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorAptitudFisicaTest {

    @Mock
    private ServicioAptitudFisica servicioAptitudFisicaMock;

    @Mock
    private RepositorioUsuario repositorioUsuarioMock;

    @InjectMocks
    private ControladorAptitudFisica controladorAptitudFisica;

    RepositorioTipoEntrenamiento repositorioTipoEntrenamiento;

    @BeforeEach
    public void init(){
        // Mock del repositorio de tipo de entrenamiento
        repositorioTipoEntrenamiento = mock(RepositorioTipoEntrenamiento.class);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcesarFormulario_ExcepcionDatosMalIngresados() throws DatosMalIngresadosException, esMenorDeEdadException {
        AptitudFisica aptitudFisica = new AptitudFisica();
        HttpServletRequest request = mock(HttpServletRequest.class);
        Usuario usuario = new Usuario();
        List<TipoEntrenamiento> entrenamientos = new ArrayList<>();
        TipoEntrenamiento entrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        entrenamientos.add(entrenamiento);

        when(repositorioUsuarioMock.buscarPorId(1L)).thenReturn(usuario);
        when(request.getParameterValues("tiposDeEntrenamiento")).thenReturn(new String[]{"prueba"});
        when(repositorioTipoEntrenamiento.findByNombre("prueba")).thenReturn(entrenamiento);

        // Configura el mock del servicio para lanzar una excepción
        doThrow(new DatosMalIngresadosException()).when(servicioAptitudFisicaMock).registrarDatos(any(AptitudFisica.class), anyList(), anyList());


        ModelAndView modelAndView = controladorAptitudFisica.procesarFormulario(1L, aptitudFisica, request);

        assertEquals("formularioAptitudFisica", modelAndView.getViewName());
        assertEquals("Todos los campos deben estar completos", modelAndView.getModel().get("error"));
    }

    @Test
    public void testProcesarFormulario_ExcepcionMenorDeEdad() throws DatosMalIngresadosException, esMenorDeEdadException {
        AptitudFisica aptitudFisica = new AptitudFisica();
        HttpServletRequest request = mock(HttpServletRequest.class);
        Usuario usuario = new Usuario();
        List<TipoEntrenamiento> entrenamientos = new ArrayList<>();
        TipoEntrenamiento entrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        entrenamientos.add(entrenamiento);
        List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new ArrayList<>();
        AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento = new AptitudFisicaTipoEntrenamiento();
        TipoEntrenamiento tipoEntrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        aptitudFisicaTipoEntrenamiento.setTipoEntrenamiento(tipoEntrenamiento);
        aptitudFisicaTipoEntrenamiento.setDias(2L);
        aptitudFisicaTipoEntrenamientos.add(aptitudFisicaTipoEntrenamiento);
        aptitudFisica.setAptitudFisicaTipoEntrenamientos(aptitudFisicaTipoEntrenamientos);
        aptitudFisica.setAltura(20);
        aptitudFisica.setPeso(20);
        aptitudFisica.setHorasEntrenamiento(10);
        aptitudFisica.setFechaNacimiento("2010-01-02");

        when(repositorioUsuarioMock.buscarPorId(1L)).thenReturn(usuario);
        when(request.getParameterValues("tiposDeEntrenamiento")).thenReturn(new String[]{"prueba"});
        when(repositorioTipoEntrenamiento.findByNombre("prueba")).thenReturn(entrenamiento);

        doThrow(new esMenorDeEdadException()).when(servicioAptitudFisicaMock).registrarDatos(any(AptitudFisica.class), anyList(), anyList());


        ModelAndView modelAndView = controladorAptitudFisica.procesarFormulario(1L, aptitudFisica, request);

        assertEquals("formularioAptitudFisica", modelAndView.getViewName());
        assertEquals("Tiene menos de 18 Años", modelAndView.getModel().get("error"));
    }

    @Test
    public void testProcesarFormulario_Exito() throws DatosMalIngresadosException, esMenorDeEdadException {
        AptitudFisica aptitudFisica = new AptitudFisica();
        Usuario usuario = new Usuario();
        List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new ArrayList<>();
        AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento = new AptitudFisicaTipoEntrenamiento();
        TipoEntrenamiento tipoEntrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        aptitudFisicaTipoEntrenamiento.setTipoEntrenamiento(tipoEntrenamiento);
        aptitudFisicaTipoEntrenamiento.setDias(2L);
        aptitudFisicaTipoEntrenamientos.add(aptitudFisicaTipoEntrenamiento);
        aptitudFisica.setAptitudFisicaTipoEntrenamientos(aptitudFisicaTipoEntrenamientos);
        aptitudFisica.setAltura(20);
        aptitudFisica.setPeso(20);
        aptitudFisica.setHorasEntrenamiento(10);

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(repositorioUsuarioMock.buscarPorId(anyLong())).thenReturn(usuario);
        Mockito.doReturn(true).when(servicioAptitudFisicaMock).sonParametrosValidos(any(AptitudFisica.class));

        ModelAndView modelAndView = controladorAptitudFisica.procesarFormulario(1L, aptitudFisica, request);

        assertEquals("redirect:/home", modelAndView.getViewName());
        verify(repositorioUsuarioMock, times(1)).guardar(usuario);
    }
}