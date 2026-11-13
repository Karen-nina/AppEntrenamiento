package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

public class DetalleRutina {
    private List<Ejercicio> listaEjercicios;
    Double pesoActual;

    // Constructores

    public DetalleRutina() {
        this.listaEjercicios = new ArrayList<Ejercicio>();
    }

    // Getters y Setters

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public Double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(Double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public void addEjercicio(Ejercicio ejercicio) {
        this.listaEjercicios.add(ejercicio);
    }


}
