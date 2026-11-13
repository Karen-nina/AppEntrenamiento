package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioTipoEntrenamiento {
    void guardar(AptitudFisica aptitudFisica);
    void guardarRelacion(Long aptitudFisicaId, Long tipoEntrenamientoId);
    List<TipoEntrenamiento> findAll();
    TipoEntrenamiento findByNombre(String nombre);
    TipoEntrenamiento findById(Long id);
}
