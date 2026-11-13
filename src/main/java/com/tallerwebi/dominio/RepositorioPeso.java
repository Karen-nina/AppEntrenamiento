package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ErrorPesoRegistroIsEmpty;

import java.util.List;

public interface RepositorioPeso {
    void save(PesoRegistro pesoRegistro) ;

    List<PesoRegistro> findByUsuarioId(Long usuarioId) throws ErrorPesoRegistroIsEmpty;

    List<PesoRegistro> findByUsuarioIdAndMes(Long usuarioId, int mes) throws ErrorPesoRegistroIsEmpty;

}
