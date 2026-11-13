package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ErrorPesoRegistroIsEmpty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RepositorioPesoImpl implements RepositorioPeso {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPesoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(PesoRegistro pesoRegistro) {
        Session session = sessionFactory.getCurrentSession();
        session.save(pesoRegistro);
    }

    @Override
    public List<PesoRegistro> findByUsuarioId(Long usuarioId) throws ErrorPesoRegistroIsEmpty {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM PesoRegistro WHERE usuario.id = :usuarioId";
        var result = session.createQuery(hql, PesoRegistro.class)
                .setParameter("usuarioId", usuarioId)
                .list();
        if (result.size() == 0) throw new ErrorPesoRegistroIsEmpty("No hay registros disponibles");
        return result;
    }
    @Override
    public List<PesoRegistro> findByUsuarioIdAndMes(Long usuarioId, int mes) throws ErrorPesoRegistroIsEmpty {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM PesoRegistro WHERE usuario.id = :usuarioId AND MONTH(fecha) = :mes";
        var result = session.createQuery(hql, PesoRegistro.class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("mes", mes)
                .list();
        if (result.size() == 0) throw new ErrorPesoRegistroIsEmpty("No hay registros disponibles para el mes seleccionado");
        return result;
    }
}