package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service()
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public Double getUserLatitud() {
        return null;
    }

    @Override
    public Double getUserLongitud() {
        return null;
    }

    @Override
    public Usuario buscarPorId(long usuarioId) {
       return repositorioUsuario.buscarPorId(usuarioId);
    }

    @Override
    public void registrarPeso(Usuario usuario, double peso) {

    }

    @Override
    public void updateData(Usuario usuario) {
        this.repositorioUsuario.updateData(usuario);
    }

}
