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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioTipsYNutricionTest {


  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private RepositorioArticulo repositorioArticulo;

    @Test
    @Transactional
    @Rollback
    public void buscarPorTipoEntrenamientoDeberiaRetornarListaDeArticulos() {
        // Arrange
        String tipoEntrenamiento = "Musculacion";
        givenExisteUnArticuloDeMusculacion();
        givenExisteUnArticuloDeMusculacion();
        givenExisteUnArticuloDeMusculacion();
        // Act
        List<Articulo> resultadoArticulosBuscados = whenBuscoUnaListaDeArticulosDeMusculacion(tipoEntrenamiento);

        // Assert
        thenTengoLaListaDeArticulos(resultadoArticulosBuscados);
    }

    private void givenExisteUnArticuloDeMusculacion() {
        Articulo articulo = new Articulo();
        articulo.setTipoEntrenamiento("Musculacion");
        sessionFactory.getCurrentSession().save(articulo);
    }
    public List<Articulo> whenBuscoUnaListaDeArticulosDeMusculacion(String tipoEntrenamiento){
       return  repositorioArticulo.buscarPorTipoEntrenamiento(tipoEntrenamiento);
    }
    private void thenTengoLaListaDeArticulos(List<Articulo> resultadoArticulosBuscados) {
        assertNotNull(resultadoArticulosBuscados);
        assertThat(resultadoArticulosBuscados.size(),equalTo(3));
    }
    @Test
    @Transactional
    @Rollback
    public void buscoPorIdYRetornoUnArticulo() {

        Long id = 1L;
        givenExisteUnArticulo();

        Articulo articulo = whenBuscoElArticulo(id);
        System.out.println(articulo);
        thenSeEncuentraElArticulo(articulo);

    }

    private void givenExisteUnArticulo() {
        Articulo articulo = new Articulo();
        sessionFactory.getCurrentSession().save(articulo);
    }

    private Articulo whenBuscoElArticulo(Long id) {
        return  repositorioArticulo.getPorId(id);
    }
    private void thenSeEncuentraElArticulo(Articulo articulo) {
        assertEquals(articulo.getId(),1);
        assertNotNull(articulo);

    }
//    @Test
//    @Transactional
//    @Rollback
//    public void queSePuedaRetornarTodosLosArticulos() {
//        // given
//        String tipo1 ="Musculacion";
//        String tipo2 = "Cardio";
//        String tipo3= "Calistenia";
//        givenArticuloDistintoTipo(tipo1);
//        givenArticuloDistintoTipo(tipo2);
//        givenArticuloDistintoTipo(tipo3);
//        // when
//        List<Articulo> resultado = whenObtengoTodosLosArticulos();
//        thenTengoLaListaDeTodosLosArticulos(resultado);
//
//    }
    private List<Articulo> whenObtengoTodosLosArticulos(){
         return repositorioArticulo.todosLosArticulos(0,10);
    }
    private void thenTengoLaListaDeTodosLosArticulos(List<Articulo> resultado){
        assertNotNull(resultado);
        assertThat(resultado.size(),equalTo(3));
    }

    private void givenArticuloDistintoTipo(String tipo ) {
        Articulo articulo = new Articulo();
        articulo.setTipoEntrenamiento(tipo);
        sessionFactory.getCurrentSession().save(articulo);
    }

}