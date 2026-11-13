package com.tallerwebi.presentacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DatosPeso {

    private Double peso;

    private String fecha;

    public DatosPeso(Double peso, String fecha) {
        this.peso = peso;
        this.fecha = fecha;
    }

    public DatosPeso() {
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getFecha() {
        if (fecha == null || fecha.isEmpty()) {
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error al convertir la fecha: " + e.getMessage());
            return null;
        }
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
