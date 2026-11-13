package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DatosMalIngresadosException;
import com.tallerwebi.dominio.excepcion.esMenorDeEdadException;

import java.util.List;

public interface ServicioAptitudFisica {


   boolean registrarDatos(AptitudFisica aptitudFisica, List<TipoEntrenamiento> tiposEntrenamiento, List<Long> dias) throws DatosMalIngresadosException, esMenorDeEdadException;

   Integer calcularEdad(String fechaNacimiento);
   Boolean sonParametrosValidos(AptitudFisica aptitudFisica);
   boolean update(AptitudFisica aptitudFisica);
}