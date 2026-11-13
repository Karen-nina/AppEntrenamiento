package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int duracion; // en minutos
    private String tipo; // gym, natacion, calistenia, running
    private String descripcion;
    private Boolean realizado;
    private Boolean primario;

    @ManyToMany(mappedBy = "ejercicios")
    private Set<RutinaDiaria> rutinasDiarias = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setPrimario(Boolean primario) {
        this.primario = primario;
    }

    public Set<RutinaDiaria> getRutinaDiaria() {
        return rutinasDiarias;
    }

    public void setRutinaDiaria(Set<RutinaDiaria> rutinaDiaria) {
        this.rutinasDiarias = rutinaDiaria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public Boolean getPrimario() {
        return primario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}