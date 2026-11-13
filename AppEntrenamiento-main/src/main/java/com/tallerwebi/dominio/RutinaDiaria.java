package com.tallerwebi.dominio;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RutinaDiaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diaSemana;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "rutina_semanal_id")
    private RutinaSemanal rutinaSemanal;
    public Long getId() {
        return id;
    }

    @ManyToMany
    @JoinTable(
            name = "rutina_diaria_ejercicio",
            joinColumns = @JoinColumn(name = "rutina_diaria_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
    )
    @OrderColumn(name = "id")
    private List<Ejercicio> ejercicios = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public RutinaSemanal getRutinaSemanal() {
        return rutinaSemanal;
    }

    public void setRutinaSemanal(RutinaSemanal rutinaSemanal) {
        this.rutinaSemanal = rutinaSemanal;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

