package com.tallerwebi.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioRutina {
    void guardar(RutinaSemanal rutinaSemanal);

    @Query("SELECT r FROM RutinaSemanal r WHERE r.usuario.id = :usuarioId")
    List<RutinaSemanal> findByUsuarioId(Long usuarioId);

}

