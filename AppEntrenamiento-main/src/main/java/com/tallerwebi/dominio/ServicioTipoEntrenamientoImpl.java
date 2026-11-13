package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTipoEntrenamientoImpl implements ServicioTipoEntrenamiento{


    private RepositorioTipoEntrenamiento repositorioTipoEntrenamiento;

    @Autowired
    public ServicioTipoEntrenamientoImpl(RepositorioTipoEntrenamiento repositorioTipoEntrenamiento){
        this.repositorioTipoEntrenamiento = repositorioTipoEntrenamiento;
    }
    @Override
    public List<TipoEntrenamiento> findAll() {
        return repositorioTipoEntrenamiento.findAll();
    }

    @Override
    public TipoEntrenamiento findById(Long id) {
        return this.repositorioTipoEntrenamiento.findById(id);
    }
}
