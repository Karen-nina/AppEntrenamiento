package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;
import com.tallerwebi.infraestructura.ServicioMembresiaImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ServicioMembresiaTest {

    private RepositorioMembresia repositorioMembresiaMock;
    private ServicioMembresia servicioMembresia;

    @BeforeEach
    public void init() {
        repositorioMembresiaMock = mock(RepositorioMembresia.class);
        servicioMembresia = new ServicioMembresiaImp(repositorioMembresiaMock);
    }


    @Test
    public void queSePuedanObtenerMembresias() {
        // Preparacion
        Long usuarioId = 1L;
        Membresia membresia1 = new Membresia();
        Membresia membresia2 = new Membresia();
        Membresia expectedMembresias = membresia1;
        when(repositorioMembresiaMock.buscarPorUsuario(usuarioId)).thenReturn(expectedMembresias);
        Membresia actualMembresias = servicioMembresia.membresiasPorId(usuarioId);
        assertEquals(expectedMembresias, actualMembresias);
        verify(repositorioMembresiaMock, times(1)).buscarPorUsuario(usuarioId);
    }
    @Test
    public void queSePuedaEliminarMembresiaPorUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Membresia membresia1 = new Membresia();
        membresia1.setUsuario(usuario);
        servicioMembresia.eliminarPorUsuario(usuario);
        verify(repositorioMembresiaMock, times(1)).eliminarPorUsuario(usuario);

    }
    @Test
    public void queSePuedaEncontrarMembresiasPendientes(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Membresia membresia1 = new Membresia();
        membresia1.setUsuario(usuario);
        when(repositorioMembresiaMock.buscarMembresiasActivasPorUsuario(usuario)).thenReturn(membresia1);
        Membresia membresiaPendiente = servicioMembresia.buscarMembresiaPendientePorUsuario(usuario);

        assertEquals(membresia1, membresiaPendiente);
        verify(repositorioMembresiaMock, times(1)).buscarMembresiasActivasPorUsuario(usuario);
    }

    @Test
    public void queSePuedaCrearUnaMembresia() {
        Membresia membresia = new Membresia();
        servicioMembresia.crearMembresia(membresia);
        verify(repositorioMembresiaMock, times(1)).crearMembresia(membresia);
    }
    @Test
    public void testEliminarPorIdExitoso() throws MembresiaNoEncontrada {
        // Preparacion
        Long membresiaId = 1L;
        when(repositorioMembresiaMock.eliminarPorId(anyLong())).thenReturn(true);
        // accion
        boolean resultado = servicioMembresia.eliminarPorId(membresiaId);
        // validacion
        assertTrue(resultado); // Verificar que el método devolvió true
    }

    @Test
    public void testEliminarPorIdMembresiaNoEncontrada() throws MembresiaNoEncontrada {
        // Preparacion
        Long membresiaId = 1L;
        // Mockear el repositorio para lanzar una excepción
        when(repositorioMembresiaMock.eliminarPorId(membresiaId))
                .thenThrow(new MembresiaNoEncontrada());
    }

}
