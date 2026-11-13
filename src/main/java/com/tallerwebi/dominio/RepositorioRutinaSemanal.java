package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;

import java.util.List;

public interface RepositorioRutinaSemanal {
    void guardar(RutinaSemanal rutinaSemanal);
    List<RutinaSemanal> buscarPorIdDeUsuario(Long id);
    RutinaSemanal rutinaSemanalPorId(Long id);
    public List<RutinaSemanal> obtenerTodasLasRutinasById(Long idUsuario) throws RutinaSemanalVacia;

    void eliminarRutinasSemanalPorUsuario(Long idUsuario);
}
