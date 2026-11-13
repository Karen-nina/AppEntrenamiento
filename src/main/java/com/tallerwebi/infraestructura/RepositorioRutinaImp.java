package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RutinaSemanal;
import com.tallerwebi.dominio.RepositorioRutina;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository

public class RepositorioRutinaImp implements RepositorioRutina {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void guardar(RutinaSemanal rutinaSemanal) {
        sessionFactory.getCurrentSession().save(rutinaSemanal);
    }

    @Override
    public List<RutinaSemanal> findByUsuarioId(Long id) {
        return null;
    }
}

