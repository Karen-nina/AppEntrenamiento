package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioEjercicio {
    void guardar(Ejercicio ejercicio);
    Ejercicio buscarPorId(Long id);
    List<Ejercicio> buscarTodosLosEjercicio();
    Ejercicio buscarPorNombre(String nombre);
}
