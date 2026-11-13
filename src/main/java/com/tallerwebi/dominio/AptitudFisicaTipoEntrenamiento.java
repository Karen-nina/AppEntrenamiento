package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
@Table(name = "AptitudFisicaTipoEntrenamiento")
public class AptitudFisicaTipoEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aptitudFisica_id")
    private AptitudFisica aptitudFisica;

    @ManyToOne
    @JoinColumn(name = "tipoEntrenamiento_id")
    private TipoEntrenamiento tipoEntrenamiento;

    private Long dias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AptitudFisica getAptitudFisica() {
        return aptitudFisica;
    }

    public void setAptitudFisica(AptitudFisica aptitudFisica) {
        this.aptitudFisica = aptitudFisica;
    }

    public TipoEntrenamiento getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(TipoEntrenamiento tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public Long getDias() {
        return dias;
    }

    public void setDias(Long dias) {
        this.dias = dias;
    }
}