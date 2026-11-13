package com.tallerwebi.dominio;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String rol;
    private String nombre;
    private String apellido;
    private Boolean activo = false;
    private Double longitud;
    private Double latitud;
    @OneToOne
    private AptitudFisica aptitudFisica;
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private List<RutinaSemanal> rutinaSemanal;
    @ManyToOne
    private Membresia membresias;
    public AptitudFisica getAptitudFisica() {
        return aptitudFisica;
    }

    public void setAptitudFisica(AptitudFisica aptitudFisica) {
        this.aptitudFisica = aptitudFisica;
    }

    public Membresia getMembresias() {
        return membresias;
    }

    public void setMembresias(Membresia membresias) {
        this.membresias = membresias;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public void setRutinaSemanal(List<RutinaSemanal> rutinaSemanal) {
        this.rutinaSemanal = rutinaSemanal;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public boolean activo() {
        return activo;
    }

    public void activar() {
        activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<RutinaSemanal> getRutinaSemanal() {
        return this.rutinaSemanal;
    }
}
