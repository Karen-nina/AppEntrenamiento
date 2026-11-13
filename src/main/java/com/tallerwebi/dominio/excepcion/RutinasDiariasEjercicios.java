package com.tallerwebi.dominio.excepcion;

import com.tallerwebi.dominio.Ejercicio;
import com.tallerwebi.dominio.RutinaDiaria;
import com.tallerwebi.dominio.RutinaSemanal;

import javax.persistence.*;

@Entity
@Table(name= "rutina_diaria_ejercicio")
public class RutinasDiariasEjercicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RutinaDiaria getRutinaDiaria() {
        return rutinaDiaria;
    }

    public void setRutinaDiaria(RutinaDiaria rutinaDiaria) {
        this.rutinaDiaria = rutinaDiaria;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    @ManyToOne(fetch = FetchType.LAZY) // Puedes cambiar a FetchType.EAGER si lo prefieres
    @JoinColumn(name = "rutina_diaria_id")
    private RutinaDiaria rutinaDiaria;

    @ManyToOne(fetch = FetchType.LAZY) // Puedes cambiar a FetchType.EAGER si lo prefieres
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;
}

