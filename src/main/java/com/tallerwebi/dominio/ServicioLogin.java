package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

public interface ServicioLogin {

    Usuario consultarUsuario(String email, String password);
    Usuario buscarPorMail(String email) throws UsuarioInexistenteException;
    void registrar(Usuario usuario) throws UsuarioExistente;

}
