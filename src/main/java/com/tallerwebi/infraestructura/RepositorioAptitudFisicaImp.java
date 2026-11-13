package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("repositorioAptitudFisica")
public class RepositorioAptitudFisicaImp implements RepositorioAptitudFisica {

    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RepositorioAptitudFisicaImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public boolean guardar(AptitudFisica aptitudFisica) {
        sessionFactory.getCurrentSession().save(aptitudFisica);
        return true;
    }

    @Override
    public boolean update(AptitudFisica aptitudFisica) {
        String updateSql = "UPDATE AptitudFisica SET altura = ?, peso = ?, fechaNacimiento = ? , horasEntrenamiento = ?, estadoFisico = ? WHERE id = ?";
        int rows = jdbcTemplate.update(
                updateSql,
                aptitudFisica.getAltura(),
                aptitudFisica.getPeso(),
                aptitudFisica.getFechaNacimiento(),
                aptitudFisica.getHorasEntrenamiento(),
                aptitudFisica.getEstadoFisico(),
                aptitudFisica.getId()
        );

        if (rows > 0) {
            String deleteRelationSql = "DELETE FROM AptitudFisicaTipoEntrenamiento WHERE aptitudFisica_id = ?";
            jdbcTemplate.update(deleteRelationSql, aptitudFisica.getId());

            String insertRelationSql = "INSERT INTO AptitudFisicaTipoEntrenamiento (aptitudFisica_id, tipoEntrenamiento_id, dias) VALUES (?, ?, ?)";
            for (AptitudFisicaTipoEntrenamiento relacion : aptitudFisica.getAptitudFisicaTipoEntrenamientos()) {
                jdbcTemplate.update(insertRelationSql, aptitudFisica.getId(), relacion.getTipoEntrenamiento().getId(), relacion.getDias());
            }
        }

        return rows > 0;
    }

    @Override
    public boolean guardarAptitudFisicaTipoEntrenamiento(AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento) {
        int rows =jdbcTemplate.update("INSERT INTO aptitudfisicatipoentrenamiento (aptitudFisica_id, tipoEntrenamiento_id, dias) VALUES (?, ?, ?)",
                aptitudFisicaTipoEntrenamiento.getAptitudFisica().getId(), aptitudFisicaTipoEntrenamiento.getTipoEntrenamiento().getId(), aptitudFisicaTipoEntrenamiento.getDias());

        return rows > 0;
    }
}
