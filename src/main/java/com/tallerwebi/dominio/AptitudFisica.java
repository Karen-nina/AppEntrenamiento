package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AptitudFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double altura;
    private double peso;
    private String fechaNacimiento;
    private int horasEntrenamiento;
    private String estadoFisico;
    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "aptitudFisica", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos = new ArrayList<>();


    // Constructor vacío
    public AptitudFisica() {
    }

    // Getters y setters para cada campo

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<TipoEntrenamiento> getTiposEntrenamiento() {
        List<TipoEntrenamiento> newList = new ArrayList<TipoEntrenamiento>();
        aptitudFisicaTipoEntrenamientos.forEach(ap ->newList.add(ap.getTipoEntrenamiento()));
        return newList;
    }


    public int getHorasEntrenamiento() {
        return horasEntrenamiento;
    }

    public void setHorasEntrenamiento(int horasEntrenamiento) {
        this.horasEntrenamiento = horasEntrenamiento;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }


    public List<AptitudFisicaTipoEntrenamiento> getAptitudFisicaTipoEntrenamientos() {
        return  this.aptitudFisicaTipoEntrenamientos;
    }

    public void setAptitudFisicaTipoEntrenamientos(List<AptitudFisicaTipoEntrenamiento> aptitudFisicaTipoEntrenamientos) {
        this.aptitudFisicaTipoEntrenamientos.clear();

        for (AptitudFisicaTipoEntrenamiento asociacion : aptitudFisicaTipoEntrenamientos) {
            asociacion.setAptitudFisica(this);
            this.aptitudFisicaTipoEntrenamientos.add(asociacion);
        }
    }

}
