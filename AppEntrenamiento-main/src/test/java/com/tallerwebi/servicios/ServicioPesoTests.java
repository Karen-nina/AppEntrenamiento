package com.tallerwebi.servicios;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.ErrorPesoRegistroIsEmpty;
import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ServicioPesoTests {

    private ServicioPeso servicioPeso;

    @Mock
    private RepositorioPeso repositorioPeso;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servicioPeso = new ServicioPesoImpl(repositorioPeso);
    }

    @Test
    public void testObtenerPesosPorMesConAlgunosDatosSuccess() throws ErrorPesoRegistroIsEmpty {
        Usuario user = new Usuario();
        Long usuarioId = 1L;
        user.setId(usuarioId);

        List<PesoRegistro> pesoRegistros = Arrays.asList(
                new PesoRegistro(user,LocalDate.of(2024, 2, 1), 71.0),
                new PesoRegistro(user,LocalDate.of(2024, 1, 1), 70.0),
                new PesoRegistro(user,LocalDate.of(2024, 3, 1), 72.0)
        );

        when(repositorioPeso.findByUsuarioId(usuarioId)).thenReturn(pesoRegistros);

        ArrayList<Double> result = servicioPeso.obtenerPesosPorAnio(usuarioId);

        assertEquals(7, result.size());
        assertEquals(70.0, result.get(0));
        assertEquals(71.0, result.get(1));
        assertEquals(72.0, result.get(2));

        verify(repositorioPeso, times(1)).findByUsuarioId(usuarioId);
    }

    @Test
    public void testObtenerPesosPorMesEmpty() throws ErrorPesoRegistroIsEmpty {
        Long usuarioId = 1L;

        when(repositorioPeso.findByUsuarioId(usuarioId)).thenThrow(new ErrorPesoRegistroIsEmpty("No hay registros disponibles"));

        ErrorPesoRegistroIsEmpty exception = assertThrows(ErrorPesoRegistroIsEmpty.class, () -> {
            servicioPeso.obtenerPesosPorAnio(usuarioId);
        });

        assertEquals("No hay registros disponibles", exception.getMessage());

        verify(repositorioPeso, times(1)).findByUsuarioId(usuarioId);
    }




}
