package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.NoHayEjerciciosCargadosException;
import com.tallerwebi.infraestructura.ServicioRutinaImp;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ServicioRutinasTest {
    ServicioRutina servicioRutina;
    RepositorioEjercicio repositorioEjercicioMock;
    RepositorioRutinaSemanal repositorioRutinaSemanalMock;
    RutinaSemanal rutinalSemanal;
    @BeforeEach
    public void init() {
        repositorioEjercicioMock = mock(RepositorioEjercicio.class);
        repositorioRutinaSemanalMock = mock(RepositorioRutinaSemanal.class);
        servicioRutina = new ServicioRutinaImp(repositorioEjercicioMock, repositorioRutinaSemanalMock);
        rutinalSemanal = mock(RutinaSemanal.class);
    }

    @Test
    public void queSePuedaCrearUnaListaDeEjercicios() {
        givenNoExisteListaDeEjercicios();
        List<Ejercicio> listaEjercicios = whenIngresarListaDeEjercicios();
        thenRetornaListaDeEjercicios(listaEjercicios);
    }

    @Test
    public void queSePuedaMarcarUnEjercicioComoRealizado() {
        List<Ejercicio> listaEjercicios = givenExisteListaDeEjercicios();
        Ejercicio unEjercicio = new Ejercicio();
        unEjercicio.setDescripcion("correr rapido");
        whenRealizarUnEjercicioDeLaLista(listaEjercicios, unEjercicio);
        thenRetornaListaDeEjercicios(listaEjercicios);
    }

    @Test
    public void queSePuedaCalcularElIMC() {
        givenExistenDatos();
        Double pesoActual = 75.5;
        Double altura = 1.65;
        Double imc = whenCalculaIMC(pesoActual, altura);
        thenRetornaIMC(imc);
    }

    @Test
    public void queSePuedaCalcularLaDiferenciaDePeso() {
        givenExistenDatos();
        Double difPeso = whenCalculaDiferenciaDePeso();
        thenRetornaDiferenciaDePeso(difPeso);
    }

    @Test
    public void queSePuedanContarLosEjericiosRealizados() throws NoHayEjerciciosCargadosException {
//        List<Ejercicio> listaEjercicios = givenExisteListaDeEjercicios();
//        Ejercicio unEjercicio = new Ejercicio("flexiones de brazos", 45);
//        whenRealizarUnEjercicioDeLaLista(listaEjercicios, unEjercicio);
//        DetalleRutina detalleRutina = new DetalleRutina();
//        detalleRutina.setListaEjercicios(listaEjercicios);
//        thenRetornaCantidadDeEjerciciosRealizados(detalleRutina);
    }

    @Test
    public void queSePuedanContarLosEjericiosPorHacer() throws NoHayEjerciciosCargadosException {
//        List<Ejercicio> listaEjercicios = givenExisteListaDeEjercicios();
//        Ejercicio algunEjercicio = new Ejercicio("sentadillas", 20);
//        whenRealizarUnEjercicioDeLaLista(listaEjercicios, algunEjercicio);
//        DetalleRutina detalleRutina = new DetalleRutina();
//        detalleRutina.setListaEjercicios(listaEjercicios);
//        thenRetornaCantidadDeEjerciciosPorHacer(detalleRutina);
    }

    private List<Ejercicio> whenRealizarUnEjercicioDeLaLista(@NotNull List<Ejercicio> listaEjercicios, Ejercicio unEjercicio) {
        for (Ejercicio actual : listaEjercicios) {
            if (actual.getDescripcion().equals(unEjercicio.getDescripcion())) {
                actual.setRealizado(Boolean.TRUE);
            }
        }
        return listaEjercicios;
    }

    private List<Ejercicio> givenExisteListaDeEjercicios() {
        List<Ejercicio> listaEjercicios = whenIngresarListaDeEjercicios();
        return listaEjercicios;
    }

    private void givenExistenDatos() {
    }

    private Double whenCalculaIMC(Double peso, Double altura) {
        return servicioRutina.calcularIMC(peso, altura);
    }

    private Double whenCalculaDiferenciaDePeso() {
        Double pesoInicial = 88.2;
        Double pesoActual = 75.5;
        return servicioRutina.calcularDiferenciaDeKilos(pesoInicial, pesoActual);
    }

    private void thenRetornaIMC(Double imc) {
        assertThat(imc, notNullValue());
    }

    private void thenRetornaDiferenciaDePeso(Double difPeso) {
        assertThat(difPeso, notNullValue());
    }

    private void thenRetornaCantidadDeEjerciciosPorHacer(DetalleRutina detalleRutina) throws NoHayEjerciciosCargadosException {
        assertEquals(servicioRutina.contarEjerciciosporHacer(detalleRutina),2);
    }

    private void thenRetornaCantidadDeEjerciciosRealizados(DetalleRutina detalleRutina) throws NoHayEjerciciosCargadosException {
        assertEquals(servicioRutina.contarEjerciciosCumplidos(detalleRutina),1);
    }

    private void givenNoExisteListaDeEjercicios() {
    }

    private List<Ejercicio> whenIngresarListaDeEjercicios() {
        Ejercicio unEjercicio = new Ejercicio();
        unEjercicio.setDescripcion("3 reps 4 series");
        Ejercicio otroEjercicio = new Ejercicio();
        otroEjercicio.setDescripcion("3 reps 4 series");
        Ejercicio algunEjercicio = new Ejercicio();
        algunEjercicio.setDescripcion("3 reps 4 series");
        DetalleRutina detalleRutina = new DetalleRutina();
        detalleRutina.addEjercicio(unEjercicio);
        detalleRutina.addEjercicio(otroEjercicio);
        detalleRutina.addEjercicio(algunEjercicio);
        return servicioRutina.cargarEjercicios(detalleRutina.getListaEjercicios());
    }

    private void thenRetornaListaDeEjercicios(List<Ejercicio> listaEjercicios) {
        assertThat(listaEjercicios, notNullValue());
    }

    @Test
    public void queSePuedaGenerarRutinaSemanal() {
        Usuario usuario = new Usuario();

        TipoEntrenamiento entrenamiento = new TipoEntrenamiento("prueba", "Musculacion");

        AptitudFisica aptitudFisica = new AptitudFisica();
        aptitudFisica.setHorasEntrenamiento(1);

        List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new ArrayList<>();
        AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento = new AptitudFisicaTipoEntrenamiento();
        aptitudFisicaTipoEntrenamiento.setTipoEntrenamiento(entrenamiento);
        aptitudFisicaTipoEntrenamiento.setDias(2L);
        aptitudFisicaTipoEntrenamientos.add(aptitudFisicaTipoEntrenamiento);

        aptitudFisica.setAptitudFisicaTipoEntrenamientos(aptitudFisicaTipoEntrenamientos);

        usuario.setAptitudFisica(aptitudFisica);

        List<Ejercicio> listEjercicios = new ArrayList<>(listaEjercicios());

        when(repositorioEjercicioMock.buscarTodosLosEjercicio()).thenReturn(listEjercicios);
        doNothing().when(repositorioRutinaSemanalMock).guardar(any(RutinaSemanal.class));

        List<RutinaSemanal> rutinaSemanal = servicioRutina.generarRutinaSemanal(usuario);


        assertNotNull(rutinaSemanal);
        assertEquals(1, rutinaSemanal.size());

        verify(repositorioRutinaSemanalMock, times(1)).guardar(any());
    }
    @Test
    public void queSePuedaGenerarDosRutinasSemanales() {
        Usuario usuario = new Usuario();
        TipoEntrenamiento entrenamientoUno = new TipoEntrenamiento("prueba", "Musculacion");
        TipoEntrenamiento entrenamientoDos = new TipoEntrenamiento("prueba", "Cardio");
        AptitudFisica aptitudFisica = new AptitudFisica();
        aptitudFisica.setHorasEntrenamiento(1);

        List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new ArrayList<>();
        AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamientoUno = new AptitudFisicaTipoEntrenamiento();
        AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamientoDos = new AptitudFisicaTipoEntrenamiento();

        aptitudFisicaTipoEntrenamientoUno.setTipoEntrenamiento(entrenamientoUno);
        aptitudFisicaTipoEntrenamientoUno.setDias(2L);

        aptitudFisicaTipoEntrenamientoDos.setTipoEntrenamiento(entrenamientoDos);
        aptitudFisicaTipoEntrenamientoDos.setDias(2L);

        aptitudFisicaTipoEntrenamientos.add(aptitudFisicaTipoEntrenamientoUno);
        aptitudFisicaTipoEntrenamientos.add(aptitudFisicaTipoEntrenamientoDos);

        aptitudFisica.setAptitudFisicaTipoEntrenamientos(aptitudFisicaTipoEntrenamientos);

        usuario.setAptitudFisica(aptitudFisica);

        List<Ejercicio> listEjercicios = new ArrayList<>(listaEjercicios());

        when(repositorioEjercicioMock.buscarTodosLosEjercicio()).thenReturn(listEjercicios);
        doNothing().when(repositorioRutinaSemanalMock).guardar(any(RutinaSemanal.class));

        List<RutinaSemanal> rutinaSemanal = servicioRutina.generarRutinaSemanal(usuario);
        

        assertNotNull(rutinaSemanal);
        assertEquals(2, rutinaSemanal.size());

        verify(repositorioRutinaSemanalMock, times(2)).guardar(any());
    }

    @Test
    public void testGenerarEjerciciosDia() {
        int horasPorSesion = 1;
        String tipoEntrenamiento = "Cardio";
        Set<Ejercicio> ejerciciosDisponibles = listaEjercicios();

        when(repositorioEjercicioMock.buscarTodosLosEjercicio()).thenReturn(new ArrayList<>(ejerciciosDisponibles));

        List<Ejercicio> ejerciciosDia = servicioRutina.generarEjerciciosDia(horasPorSesion, tipoEntrenamiento);

        assertNotNull(ejerciciosDia);
        assertTrue(ejerciciosDia.size() <= horasPorSesion * 60);
        int totalDuracion = ejerciciosDia.stream().mapToInt(Ejercicio::getDuracion).sum();
        assertTrue(totalDuracion <= horasPorSesion * 60);
        long cantidadEjerciciosDeCardio = ejerciciosDia.stream().filter(ejercicio -> "Cardio".equals(ejercicio.getTipo())).count();
        assertTrue(cantidadEjerciciosDeCardio > 0);
        verify(repositorioEjercicioMock, times(1)).buscarTodosLosEjercicio();
    }

    public Set<Ejercicio> listaEjercicios(){
        Set <Ejercicio>list = new HashSet<>();
        Ejercicio ejercicio1 = new Ejercicio();
        Ejercicio ejercicio2 = new Ejercicio();
        Ejercicio ejercicio3 = new Ejercicio();
        ejercicio1.setDescripcion("correr rapido");
        ejercicio1.setDuracion(2);
        ejercicio1.setNombre("correr");
        ejercicio1.setTipo("Cardio");
        ejercicio1.setPrimario(Boolean.TRUE);
        ejercicio2.setDescripcion("correr rapido1");
        ejercicio2.setDuracion(40);
        ejercicio2.setNombre("correr1");
        ejercicio2.setTipo("Cardio");
        ejercicio2.setPrimario(Boolean.TRUE);
        ejercicio3.setDescripcion("correr rapido1");
        ejercicio3.setDuracion(40);
        ejercicio3.setNombre("correr1");
        ejercicio3.setTipo("Cardio");
        ejercicio3.setPrimario(Boolean.TRUE);
        list.add(ejercicio1);
        list.add(ejercicio2);
        list.add(ejercicio3);
        return list;
    }
}
