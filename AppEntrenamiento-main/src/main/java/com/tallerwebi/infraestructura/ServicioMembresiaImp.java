package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Membresia;
import com.tallerwebi.dominio.RepositorioMembresia;
import com.tallerwebi.dominio.ServicioMembresia;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioMembresiaImp implements ServicioMembresia {
    @Autowired
    RepositorioMembresia repositorioMembresia;

    public ServicioMembresiaImp (RepositorioMembresia repositorioMembresia){
        this.repositorioMembresia = repositorioMembresia;
    }
    @Override
    public Membresia membresiasPorId(Long usuarioId) {
        return repositorioMembresia.buscarPorUsuario(usuarioId);
    }

    @Override
    public void crearMembresia(Membresia membresia) {
        repositorioMembresia.crearMembresia(membresia);
    }

    @Override
    public Membresia buscarPorId(Long id) {
        return repositorioMembresia.buscarPorId(id);
    }

    @Override
    public Membresia buscarMembresiaPendientePorUsuario(Usuario usuario) {
        return repositorioMembresia.buscarMembresiasActivasPorUsuario(usuario);
    }

    @Override
    public Boolean eliminarPorId(Long membresiaId) throws MembresiaNoEncontrada {
      return  repositorioMembresia.eliminarPorId(membresiaId);
    }

    @Override
    public void actualizarMembresia(Membresia membresia) {
        repositorioMembresia.crearMembresia(membresia);
    }

    @Override
    public void eliminarPorUsuario(Usuario usuario) {
        repositorioMembresia.eliminarPorUsuario(usuario);
    }

}
