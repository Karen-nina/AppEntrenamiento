package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.AptitudFisica;
import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.TipoEntrenamiento;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario buscarUsuario(String email, String password) {

        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return sessionFactory.getCurrentSession().find(Usuario.class, id);
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public Usuario buscar(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void modificar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public void updateData(Usuario usuario) {
        String updateSql = "UPDATE Usuario SET nombre = ?, apellido = ?, email = ?, latitud = ?, longitud = ? WHERE id = ?";
        jdbcTemplate.update(
                updateSql,
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getLatitud(),
                usuario.getLongitud(),
                usuario.getId()
        );
    }
}
