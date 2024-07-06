package com.tallerwebi.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String ubicacion;
    private Double longitud;
    private Double latitud;
    @Transient
    private Double distancia;  // No persistir la distancia calculada, es una propiedad derivada

    @ManyToMany
    @JoinTable(
            name = "Lugar_TipoEntrenamiento",
            joinColumns = @JoinColumn(name = "lugar_id"),
            inverseJoinColumns = @JoinColumn(name = "tipoEntrenamiento_id")
    )
    private Set<TipoEntrenamiento> tiposEntrenamiento = new HashSet<>();

    public Lugar(String nombre, String ubicacion, Double longitud, Double latitud) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
}
