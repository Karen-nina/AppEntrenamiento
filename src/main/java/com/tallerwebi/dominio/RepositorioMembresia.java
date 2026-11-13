package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;

public interface RepositorioMembresia {
    Membresia buscarPorTipo(String tipoMembresia);
    Membresia buscarPorUsuario(Long usuarioId);
    void crearMembresia(Membresia membresia);

    Membresia buscarPorId(Long id);
    Membresia buscarMembresiasActivasPorUsuario(Usuario usuario);
    Boolean eliminarPorId(Long membresiaId) throws MembresiaNoEncontrada;

    void eliminarPorUsuario(Usuario usuario);
}

