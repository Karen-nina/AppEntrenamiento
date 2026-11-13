package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DatosMalIngresadosException;
import com.tallerwebi.dominio.excepcion.esMenorDeEdadException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.tallerwebi.infraestructura.ServicioAptitudFisicaImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ServicioFormularioAptitudFisicaTest {
    ServicioAptitudFisica servicioAptitudFisica;
    RepositorioAptitudFisica repositorioAptitudFisica;
    RepositorioTipoEntrenamiento repositorioTipoEntrenamiento;

    @BeforeEach
    public void init() {
        repositorioTipoEntrenamiento = mock(RepositorioTipoEntrenamiento.class);
        repositorioAptitudFisica = mock(RepositorioAptitudFisica.class);
        servicioAptitudFisica = new ServicioAptitudFisicaImp(repositorioAptitudFisica, repositorioTipoEntrenamiento);
    }

    private void givenNoHayDatos() {
    }
    private void thenRetornaAptitudFisica(AptitudFisica apto) {
        assertThat(apto, notNullValue());
        assertThat(apto.getAltura(), equalTo(185));
    }
    @Test
    public void siLaEdadEsMenorDe18LanceUnExcepcion() {
        givenNoHayDatos();
        whenLaEdadEsMenoa18();
    }

    private void whenLaEdadEsMenoa18() {
        TipoEntrenamiento entrenamiento = new TipoEntrenamiento("prueba", "Musculacion");
        when(repositorioTipoEntrenamiento.findByNombre("prueba")).thenReturn(entrenamiento);

        LocalDate fechaDeNacimiento = LocalDate.of(2010, 1, 1);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaDeNacimientoString = fechaDeNacimiento.format(formato);

        AptitudFisica apto = new AptitudFisica();
        apto.setAltura(185);
        apto.setPeso(100.5);
        apto.setFechaNacimiento(fechaDeNacimientoString);
        apto.setHorasEntrenamiento(1);
        apto.setEstadoFisico("sedentario");

        List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new ArrayList<>();
        AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento = new AptitudFisicaTipoEntrenamiento();
        aptitudFisicaTipoEntrenamiento.setTipoEntrenamiento(entrenamiento);
        aptitudFisicaTipoEntrenamientos.add(aptitudFisicaTipoEntrenamiento);

        apto.setAptitudFisicaTipoEntrenamientos(aptitudFisicaTipoEntrenamientos); // Configurar tipos de entrenamiento en AptitudFisica

        assertThrows(esMenorDeEdadException.class,
                () -> servicioAptitudFisica.registrarDatos(apto, apto.getTiposEntrenamiento(), null));
    }
    @Test
    public void siFaltaAlgunDatoQueEnvieUnExcepcion() {
        givenNoHayDatos();
        whenFaltaAlgunDato();
    }

    private void whenFaltaAlgunDato() {
        AptitudFisica apto = new AptitudFisica();
        apto.setAltura(185);
        apto.setPeso(100.5);
        LocalDate fechaDeNacimiento = LocalDate.of(2018, 1, 31);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<TipoEntrenamiento> entrenamientosLis = new ArrayList<>();
        TipoEntrenamiento entrenamiento = new TipoEntrenamiento();
        entrenamientosLis.add(entrenamiento);

        String fechaDeNacimientoString = fechaDeNacimiento.format(formato);
        apto.setFechaNacimiento(fechaDeNacimientoString);
        apto.setEstadoFisico("sedentario");

        List<Long> dias = Arrays.asList(3L);

        assertThrows(DatosMalIngresadosException.class,
                () -> servicioAptitudFisica.registrarDatos(apto, entrenamientosLis, dias));
    }
}
