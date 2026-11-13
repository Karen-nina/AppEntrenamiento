package com.tallerwebi.dominio;

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
public class RepositorioEjercicioTest {


    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RepositorioEjercicio repositorioEjercicio;

    @Test
    @Transactional
    @Rollback
    public void buscarPorIdRetornarEjercicioCorrecto() {
        Long id = 1L;
        givenExisteUnEjercicio();
        givenExisteUnEjercicio();
        givenExisteUnEjercicio();
        Ejercicio ejercicioBuscado = whenBuscoUnEjercicioPorId(id);
        thenObtengoElEjercicio(ejercicioBuscado);
    }

    private void givenExisteUnEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        sessionFactory.getCurrentSession().save(ejercicio);
    }


    public Ejercicio whenBuscoUnEjercicioPorId(Long id){
        return  repositorioEjercicio.buscarPorId(id);
    }
    private void thenObtengoElEjercicio(Ejercicio ejercicioBuscado) {
        assertNotNull(ejercicioBuscado);
    }





//    @Test
//    @Transactional
//    @Rollback
//    public void buscarTodosLosEjercicio() {
//        // Given
//        givenExisteUnEjercicio();
//        givenExisteUnEjercicio();
//        givenExisteUnEjercicio();
//
//        // When
//        List<Ejercicio> lista = repositorioEjercicio.buscarTodosLosEjercicio();
//
//        // Then
//        assertThat(lista.size(), equalTo(3));
//    }
//

}