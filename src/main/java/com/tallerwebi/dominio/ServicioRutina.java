package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.NoHayEjerciciosCargadosException;
import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;
import com.tallerwebi.presentacion.DatosDiasYEjercicios;

import java.util.List;

public interface ServicioRutina {

    List<Ejercicio> cargarEjercicios(List<Ejercicio> listaEjercicios);
    Integer contarEjerciciosCumplidos(DetalleRutina detalleRutina) throws NoHayEjerciciosCargadosException;
    Double calcularDiferenciaDeKilos(Double pesoInicial, Double pesoActual);
    Integer contarEjerciciosporHacer(DetalleRutina detalleRutina) throws NoHayEjerciciosCargadosException;
    Double calcularIMC(Double peso,Double altura);



    List<RutinaSemanal> generarRutinaSemanal(Usuario usuario);
    List<Ejercicio> generarEjerciciosDia(int horasPorSesion, String tipoEntrenamiento);
    List<RutinaSemanal>buscarPorIdDeUsuario(Long id);



    List<RutinaSemanal> obtenerTodasLasRutinasById(Long idUsuario) throws RutinaSemanalVacia;
    DatosDiasYEjercicios obtenerDatosDiasYEjercicios(Long idUsuario) throws RutinaSemanalVacia;
    DatosDiasYEjercicios procesarRutinas(Long idUsuario) throws RutinaSemanalVacia;
}
