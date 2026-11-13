package com.tallerwebi.dominio;

public interface ServicioUsuario{

    Double getUserLatitud();
    Double getUserLongitud();
    Usuario buscarPorId(long usuarioId);
    void registrarPeso(Usuario usuario, double peso);

    void updateData(Usuario usuario);

}
