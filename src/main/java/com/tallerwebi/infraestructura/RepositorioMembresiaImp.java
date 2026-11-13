package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Membresia;
import com.tallerwebi.dominio.RepositorioMembresia;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioMembresiaImp implements RepositorioMembresia {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMembresiaImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Membresia buscarPorTipo(String tipo) {
        final Session session = sessionFactory.getCurrentSession();
        return (Membresia) session.createCriteria(Membresia.class)
                .add(Restrictions.eq("tipo", tipo))
                .uniqueResult();
    }

    @Override
    public Membresia buscarPorUsuario(Long usuarioId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Membresia where usuario_id = :usuarioId", Membresia.class)
                .setParameter("usuarioId", usuarioId)
                .uniqueResult();
    }

    @Override
    public void crearMembresia(Membresia membresia) {
        sessionFactory.getCurrentSession().save(membresia);
    }

    @Override
    public Membresia buscarPorId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Membresia where id = :id", Membresia.class)
                .setParameter("id", id)
                .uniqueResult();
    }
    @Override
    public Membresia buscarMembresiasActivasPorUsuario(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Membresia where usuario = :usuario and estado = 'PENDIENTE'", Membresia.class)
                .setParameter("usuario", usuario)
                .uniqueResult();
    }

    @Override
    public Boolean eliminarPorId(Long membresiaId) throws MembresiaNoEncontrada {
        Session session = sessionFactory.getCurrentSession();
        Membresia membresia = buscarPorId(membresiaId);
        if(membresia !=null){
            session.delete(membresia);
        }else{
          throw new MembresiaNoEncontrada();
        }
        return true;
    }

    @Override
    public void eliminarPorUsuario(Usuario usuario){
        Session session = sessionFactory.getCurrentSession();

        // Buscar todas las membresías asociadas con el usuario
        List<Membresia> membresias = session.createQuery("from Membresia where usuario = :usuario", Membresia.class)
                .setParameter("usuario", usuario)
                .list();

        // Eliminar todas las membresías encontradas
        for (Membresia membresia : membresias) {
            session.delete(membresia);
        }
    }


}
