package com.tallerwebi.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioRutinaDiaria {
    @Query("SELECT r FROM RutinaDiaria r WHERE r.rutina_semanal_id = :rutinaSemanal")
    List<RutinaDiaria> getListadoDeRutinasDiarias(Long rutinaSemanal);
}
