package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioMembresiaTest {


    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RepositorioMembresia repositorioMembresia;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void cuandoBuscoPorTipoDeMembresia() {
        // Preparacion
        String tipo = "Musculacion";
        Membresia membresia = new Membresia();
        repositorioMembresia.crearMembresia(membresia);
        membresia.setTipo(tipo);
        // ejecucion
      Membresia resultado = repositorioMembresia.buscarPorTipo(tipo);
    System.out.println(resultado);
        // validacion
        assertEquals(tipo, resultado.getTipo());
    }


    @Test
    @Transactional
    @Rollback
    public void cuandoBuscoMembresiaPorUsuario() {
        // Preparacion
        Long idusuario = 1L;
        Membresia membresia = new Membresia();
        Usuario usuario  = new Usuario();
       membresia.setUsuario(usuario);
       membresia.setDuracion(100);
        repositorioUsuario.guardar(usuario);
        repositorioMembresia.crearMembresia(membresia);
        // ejecucion
        Membresia resultado = repositorioMembresia.buscarPorUsuario(idusuario);
        // validacion
        assertEquals(100,resultado.getDuracion());
    }
    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarMembresia() throws MembresiaNoEncontrada {
        //prepa
        Long membresiaId = 3L;
        Membresia membresia = new Membresia();
        membresia.setId(membresiaId);
        repositorioMembresia.crearMembresia(membresia);
        System.out.println(membresia.getId());
        //ejecucion
        Boolean resultado = repositorioMembresia.eliminarPorId(membresiaId);
        //validacion
        assertTrue(resultado);

    }
}