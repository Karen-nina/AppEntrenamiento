package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Lugar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional
public class LugarRepositorioImpl implements LugarRepositorio {

    private SessionFactory sessionFactory;

    @Autowired
    public LugarRepositorioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Lugar> obtenerTodosLosLugares() {
        Session session = sessionFactory.getCurrentSession();
        Query<Lugar> query = session.createQuery("from Lugar", Lugar.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Lugar> buscarLugaresPorTipoActividad(Long tipoActividad) {
        Session session = sessionFactory.getCurrentSession();
        Query<Lugar> query = session.createQuery(
                "select l from Lugar l join l.tiposEntrenamiento t where t.id = :tipoActividad", Lugar.class
        );
        query.setParameter("tipoActividad", tipoActividad);
        return query.getResultList();
    }
}