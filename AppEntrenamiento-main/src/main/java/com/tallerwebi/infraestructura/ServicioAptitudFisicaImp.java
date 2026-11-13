package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosMalIngresadosException;
import com.tallerwebi.dominio.excepcion.esMenorDeEdadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioAptitudFisicaImp implements ServicioAptitudFisica {
    @Autowired
    private  RepositorioAptitudFisica repositorioAptitudFisica;
    @Autowired
    private  RepositorioUsuario repoUsuario;

    @Autowired
    private  RepositorioTipoEntrenamiento repoEntrenamiento;
    public ServicioAptitudFisicaImp (RepositorioAptitudFisica repositorioAptitudFisica, RepositorioTipoEntrenamiento repoEntrenamiento){
        this.repositorioAptitudFisica = repositorioAptitudFisica;
        this.repoEntrenamiento = repoEntrenamiento;
    }

    @Override
    @Transactional
    public boolean registrarDatos(AptitudFisica aptitudFisica, List<TipoEntrenamiento> tiposEntrenamiento, List<Long> dias) throws DatosMalIngresadosException, esMenorDeEdadException {
        if (!sonParametrosValidos(aptitudFisica)) {
            throw new DatosMalIngresadosException();
        }
        if (!esMayorDeEdad(aptitudFisica.getFechaNacimiento())) {
            throw new esMenorDeEdadException();
        }

        List<AptitudFisicaTipoEntrenamiento> aptitudes = new ArrayList<>();
        for (int i = 0; i < tiposEntrenamiento.size(); i++) {
            AptitudFisicaTipoEntrenamiento aptitud = new AptitudFisicaTipoEntrenamiento();
            aptitud.setAptitudFisica(aptitudFisica);
            aptitud.setTipoEntrenamiento(tiposEntrenamiento.get(i));
            aptitud.setDias(dias.get(i));
            aptitudes.add(aptitud);
        };

        aptitudFisica.setAptitudFisicaTipoEntrenamientos(aptitudes);

        return repositorioAptitudFisica.guardar(aptitudFisica);
    }

    @Override
    public Integer calcularEdad(String fechaNacimiento) {
        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento);
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimientoDate, fechaActual).getYears();
    }

    @Override
    public Boolean sonParametrosValidos(AptitudFisica aptitudFisica) {
        return aptitudFisica.getAltura() > 0
                && aptitudFisica.getPeso() > 0
                && aptitudFisica.getTiposEntrenamiento() != null
                && !aptitudFisica.getTiposEntrenamiento().isEmpty()
                && aptitudFisica.getFechaNacimiento() != null
                && !aptitudFisica.getFechaNacimiento().isEmpty()
                && aptitudFisica.getHorasEntrenamiento() > 0
                && aptitudFisica.getEstadoFisico() != null
                && !aptitudFisica.getEstadoFisico().isEmpty();
    }

    @Override
    public boolean update(AptitudFisica aptitudFisica) {
        return repositorioAptitudFisica.update(aptitudFisica);
    }

    public Boolean esMayorDeEdad(String edad){
        if (calcularEdad(edad) >=18) {
            return true;
        }
        return false;
    }


}