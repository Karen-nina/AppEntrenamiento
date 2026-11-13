package com.tallerwebi.presentacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatosDiasYEjercicios {
    private Map<String, Integer> dias;
    private Map<String, Integer> ejercicios;

    private ArrayList<Integer> diasList;
    private ArrayList<Integer> ejerciciosList;
    private List<String> labelDias;
    private List<String> labelEjercicios;

    public DatosDiasYEjercicios() {
        this.dias = new HashMap<>();
        this.ejercicios = new HashMap<>();

        this.dias.put("Lunes", 0);
        this.dias.put("Martes", 0);
        this.dias.put("Miércoles", 0);
        this.dias.put("Jueves", 0);
        this.dias.put("Viernes", 0);
        this.dias.put("Sábado", 0);

    }

    public void agregarDia(String dia) {
        if (this.dias.containsKey(dia)) {
            this.dias.put(dia, this.dias.get(dia) + 1);
        }
    }

    public void agregarEjercicio(String ejercicio) {
        if (this.ejercicios.containsKey(ejercicio)) {
            this.ejercicios.put(ejercicio, this.ejercicios.get(ejercicio) + 1);
        }else{
            this.ejercicios.put(ejercicio,1);
        }
    }

    public Map<String, Integer> getDias() {
        return dias;
    }

    public void setDias(Map<String, Integer> dias) {
        this.dias = dias;
    }

    public Map<String, Integer> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Map<String, Integer> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<Integer> getDiasList() {
        return diasList;
    }

    public void setDiasList(ArrayList<Integer> diasList) {
        this.diasList = diasList;
    }

    public ArrayList<Integer> getEjerciciosList() {
        return ejerciciosList;
    }

    public void setEjerciciosList(ArrayList<Integer> ejerciciosList) {
        this.ejerciciosList = ejerciciosList;
    }

    public List<String> getLabelDias() {
        return labelDias;
    }

    public void setLabelDias(List<String> labelDias) {
        this.labelDias = labelDias;
    }

    public List<String> getLabelEjercicios() {
        return labelEjercicios;
    }

    public void setLabelEjercicios(List<String> labelEjercicios) {
        this.labelEjercicios = labelEjercicios;
    }
}
