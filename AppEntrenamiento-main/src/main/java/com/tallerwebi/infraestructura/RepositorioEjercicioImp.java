package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Ejercicio;
import com.tallerwebi.dominio.RepositorioEjercicio;
import com.tallerwebi.dominio.RutinaSemanal;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioEjercicioImp implements RepositorioEjercicio {
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioEjercicioImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void guardar(Ejercicio ejercicio) {
        sessionFactory.getCurrentSession().save(ejercicio);
    }

    @Override
    public Ejercicio buscarPorId(Long id) {
        return   sessionFactory.getCurrentSession().find(Ejercicio.class, id);
    }

    @Override
    public List<Ejercicio> buscarTodosLosEjercicio() { Session session = sessionFactory.openSession();
        Query<Ejercicio> query = session.createQuery("SELECT e FROM Ejercicio e", Ejercicio.class);
        return query.getResultList();
    }

    @Override
    public Ejercicio buscarPorNombre(String nombre) {
        Session session = sessionFactory.openSession();
        return (Ejercicio) session.createCriteria(Ejercicio.class)
                .add(Restrictions.eq("nombre", nombre))
                .uniqueResult();

    }
}
