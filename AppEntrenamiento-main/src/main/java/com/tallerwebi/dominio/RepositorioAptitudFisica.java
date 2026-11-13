package com.tallerwebi.dominio;

public interface RepositorioAptitudFisica
{
    boolean guardar(AptitudFisica aptitudFisica);

    boolean update(AptitudFisica aptitudFisica);

    boolean guardarAptitudFisicaTipoEntrenamiento(AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento);
}