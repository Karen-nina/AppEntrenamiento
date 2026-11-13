package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioArticulo {
    List<Articulo> buscarPorTipoEntrenamiento(String tipoEntrenamiento);
    List<Articulo> todosLosArticulos(int page, int size);

    Articulo getPorId(Long id);
    Long contarTotalArticulos();
}
