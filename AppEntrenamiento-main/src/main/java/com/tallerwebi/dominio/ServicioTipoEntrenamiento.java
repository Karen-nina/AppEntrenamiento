package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioTipoEntrenamiento {
    List<TipoEntrenamiento> findAll();

    TipoEntrenamiento findById(Long id);
}
