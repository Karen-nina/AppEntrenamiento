package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ErrorPesoRegistroIsEmpty;
import com.tallerwebi.presentacion.DatosPeso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioPesoImpl implements ServicioPeso{


    public RepositorioPeso repositorioPeso;

    @Autowired
    public ServicioPesoImpl(RepositorioPeso repositorioPeso){
        this.repositorioPeso = repositorioPeso;
    }

    @Override
    public ArrayList<Double> obtenerPesosPorMes(Long usuarioId, int mes) throws ErrorPesoRegistroIsEmpty {
        List<PesoRegistro> pesoRegistro;
        pesoRegistro = this.repositorioPeso.findByUsuarioIdAndMes(usuarioId, mes);


        ArrayList<Double> pesosDelMes = new ArrayList<>();

        for (PesoRegistro p : pesoRegistro) {
            pesosDelMes.add(p.getPeso());
        }

        return pesosDelMes;
    }

    @Override
    public ArrayList<Double> obtenerPesosPorAnio(Long usuarioId) throws ErrorPesoRegistroIsEmpty {
        List<PesoRegistro> pesoRegistro;
        try {
            pesoRegistro = this.repositorioPeso.findByUsuarioId(usuarioId);
        } catch (ErrorPesoRegistroIsEmpty e) {
            throw new ErrorPesoRegistroIsEmpty(e.getMessage());
        }

        Map<Integer, Double> pesosPorMes = new HashMap<>();

        for (PesoRegistro p : pesoRegistro) {
            LocalDate fecha = p.getFecha();
            int mes = fecha.getMonthValue();
            double peso = p.getPeso();
            pesosPorMes.put(mes, peso);
        }

        ArrayList<Double> pesosOrdenadosPorMes = new ArrayList<>();

        int mesActual = LocalDate.now().getMonthValue();

        for (int i = 1; i <= 12; i++) {
            if (i > mesActual) {
                break;
            }

            if (pesosPorMes.containsKey(i)) {
                pesosOrdenadosPorMes.add(pesosPorMes.get(i));
            } else {
                if (i > 1) {
                    double pesoAnterior = pesosOrdenadosPorMes.get(i - 2);
                    pesosOrdenadosPorMes.add(pesoAnterior);
                } else {
                    pesosOrdenadosPorMes.add(0.0);
                }
            }
        }

        return pesosOrdenadosPorMes;
    }

    @Override
    public void postPeso(Usuario usuario, DatosPeso datos) throws ErrorPesoRegistroIsEmpty {
        PesoRegistro newRegistro = new PesoRegistro(usuario,datos.getFecha(),datos.getPeso());
            this.repositorioPeso.save(newRegistro);
    }
}
