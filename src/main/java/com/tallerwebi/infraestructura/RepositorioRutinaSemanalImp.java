package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;

@Repository
public class RepositorioRutinaSemanalImp implements RepositorioRutinaSemanal {
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioRutinaSemanalImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void guardar(RutinaSemanal rutinaSemanal) {
        sessionFactory.getCurrentSession().save(rutinaSemanal);
    }

    @Override
    public void eliminarRutinasSemanalPorUsuario(Long idUsuario) {
        List<RutinaSemanal> rutinas = buscarPorIdDeUsuario(idUsuario);

        for (RutinaSemanal rutina : rutinas) {
                sessionFactory.getCurrentSession().delete(rutina);
        }
    }

    public List<RutinaSemanal> buscarPorIdDeUsuario(Long idUsuario) {
        Session session = sessionFactory.getCurrentSession();
        Query<RutinaSemanal> query = session.createQuery(
                "SELECT DISTINCT rs " +
                        "FROM RutinaSemanal rs " +
                        "LEFT JOIN FETCH rs.rutinaDiaria rd " +
                        "LEFT JOIN FETCH rd.ejercicios e " +
                        "LEFT JOIN RutinasDiariasEjercicios rde " +
                        "ON rd.id = rde.rutinaDiaria.id " + // Join con la tabla pivot
                        "WHERE rs.usuario.id = :idUsuario ",
                        RutinaSemanal.class
        );
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    @Override
    public RutinaSemanal rutinaSemanalPorId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from RutinaSemanal where id = :id", RutinaSemanal.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<RutinaSemanal> obtenerTodasLasRutinasById(Long idUsuario) throws RutinaSemanalVacia {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RutinaSemanal> criteriaQuery = criteriaBuilder.createQuery(RutinaSemanal.class);
        Root<RutinaSemanal> root = criteriaQuery.from(RutinaSemanal.class);
        Join<RutinaSemanal, Usuario> usuarioJoin = root.join("usuario");
        criteriaQuery.select(root).where(criteriaBuilder.equal(usuarioJoin.get("id"), idUsuario));
        var results = session.createQuery(criteriaQuery).getResultList();
        if  (results == null || results.size() == 0) throw new RutinaSemanalVacia("No se encontró una rutina");
        return results ;
    }

}
