//package com.tallerwebi.repositorios;
//
//import com.tallerwebi.dominio.RepositorioRutinaSemanal;
//import com.tallerwebi.dominio.RutinaSemanal;
//import com.tallerwebi.dominio.Usuario;
//import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;
//import com.tallerwebi.infraestructura.RepositorioRutinaSemanalImp;
//
//import static org.mockito.Mockito.*;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.exception.GenericJDBCException;
//import org.hibernate.query.Query;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Join;
//import javax.persistence.criteria.Root;
//import java.util.Collections;
//import java.util.List;
//import org.mockito.InjectMocks;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//
//public class RepositorioRutinaSemanalImpTest {
//
//    private RepositorioRutinaSemanal repositorio;
//
//    @Mock
//    private SessionFactory sessionFactory;
//
//    @Mock
//    private Session session;
//
//    @Mock
//    private Query<RutinaSemanal> query;
//
//    @Mock
//    private CriteriaBuilder criteriaBuilder;
//
//    @Mock
//    private CriteriaQuery<RutinaSemanal> criteriaQuery;
//
//    @Mock
//    private Root<RutinaSemanal> root;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        repositorio = new RepositorioRutinaSemanalImp(sessionFactory);
//        when(sessionFactory.getCurrentSession()).thenReturn(session);
//    }
//
//    @Test
//    public void RutinaSemanalGuardar() {
//        RutinaSemanal rutinaSemanal = new RutinaSemanal();
//        repositorio.guardar(rutinaSemanal);
//        verify(session, times(1)).save(rutinaSemanal);
//    }
//
//    @Test
//    public void NoSeEncuntraRutinaSemanal() {
//        Long idUsuario = 2L;
//        when(sessionFactory.openSession()).thenReturn(session);
//        when(session.createQuery(anyString(), eq(RutinaSemanal.class))).thenReturn(query);
//        when(query.setParameter(anyString(), any())).thenReturn(query);
//        when(query.uniqueResult()).thenReturn(null);
//
//        List<RutinaSemanal> rutinas = repositorio.buscarPorIdDeUsuario(idUsuario);
//        assertNull(rutinas);
//    }
//
//    @Test
//    public void SeObtieneRutinaSemanal() throws RutinaSemanalVacia {
//        Long idUsuario = 2L;
//        RutinaSemanal rutinaEsperada = new RutinaSemanal();
//        when(sessionFactory.openSession()).thenReturn(session);
//        when(session.createQuery(anyString(), eq(RutinaSemanal.class))).thenReturn(query);
//        when(query.setParameter(anyString(), any())).thenReturn(query);
//        when(query.uniqueResult()).thenReturn(rutinaEsperada);
//
//        List<RutinaSemanal> rutinaActual = repositorio.buscarPorIdDeUsuario(idUsuario);
//
//        assertNotNull(rutinaActual);
//        assertEquals(rutinaEsperada, rutinaActual);
//    }
//
//    @Test
//    public void NoSeEncuntraRutinaSemanalEnLaLista() {
//        Long idUsuario = 2L;
//
//        when(sessionFactory.getCurrentSession()).thenReturn(session);
//
//        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//
//        when(criteriaBuilder.createQuery(RutinaSemanal.class)).thenReturn(criteriaQuery);
//
//        when(criteriaQuery.from(RutinaSemanal.class)).thenReturn(root);
//
//        when(root.join("usuario")).thenReturn(mock());
//
//        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
//
//        Query<RutinaSemanal> queryMock = mock(Query.class);
//
//        when(session.createQuery(criteriaQuery)).thenReturn(queryMock);
//
//        when(queryMock.getResultList()).thenReturn(Collections.emptyList());
//
//        RutinaSemanalVacia exception = assertThrows(RutinaSemanalVacia.class, () -> {
//            repositorio.obtenerTodasLasRutinasById(idUsuario);
//        });
//
//        assertEquals("No se encontró una rutina", exception.getMessage());
//    }
//
//
//}
