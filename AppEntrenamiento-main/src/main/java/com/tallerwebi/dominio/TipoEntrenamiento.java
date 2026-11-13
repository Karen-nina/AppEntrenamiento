package com.tallerwebi.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class TipoEntrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;


    @OneToMany(mappedBy = "tipoEntrenamiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new HashSet<>();

    public TipoEntrenamiento(String nombre, String descripcion) {
        this.nombre = nombre;
    }

    public TipoEntrenamiento(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
    }


    public TipoEntrenamiento (){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}