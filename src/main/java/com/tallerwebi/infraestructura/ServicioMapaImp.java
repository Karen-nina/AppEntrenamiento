package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Lugar;
import com.tallerwebi.dominio.ServicioMapa;
import com.tallerwebi.dominio.excepcion.SearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("servicioSearch")
@Transactional
public class ServicioMapaImp implements ServicioMapa {

    private final LugarRepositorio lugarRepositorio;

    @Autowired
    public ServicioMapaImp(LugarRepositorio lugarRepositorio) {
        this.lugarRepositorio = lugarRepositorio;
    }

    @Override
    public List<Lugar> buscarSitios(double latitudUsuario, double longitudUsuario) throws SearchException {
        try {
            List<Lugar> lugares = lugarRepositorio.obtenerTodosLosLugares();
            for (Lugar lugar : lugares) {
                double distancia = calcularDistancia(latitudUsuario, longitudUsuario, lugar.getLatitud(), lugar.getLongitud());
                lugar.setDistancia(distancia);
            }
            return lugares;
        } catch (Exception e) {
            throw new SearchException("Error al buscar todos los lugares");
        }
    }

    @Override
    public List<Lugar> buscarLugaresPorTipoActividad(Long tipoActividad, double latitudUsuario, double longitudUsuario) throws SearchException {
        try {
            List<Lugar> lugares = lugarRepositorio.buscarLugaresPorTipoActividad(tipoActividad);
            for (Lugar lugar : lugares) {
                double distancia = calcularDistancia(latitudUsuario, longitudUsuario, lugar.getLatitud(), lugar.getLongitud());
                lugar.setDistancia(distancia);
            }
            return lugares;
        } catch (Exception e) {
            throw new SearchException("Error al buscar lugares por tipo de actividad");
        }
    }

    @Override
    public List<Lugar> filtrarLugaresPorDistancia(List<Lugar> lugares, double latitudUsuario, double longitudUsuario, double distancia) {
        List<Lugar> lugaresFiltrados = new ArrayList<>();
        for (Lugar lugar : lugares) {
            double distanciaCalculada = calcularDistancia(latitudUsuario, longitudUsuario, lugar.getLatitud(), lugar.getLongitud());
            if (distanciaCalculada <= distancia) {
                lugar.setDistancia(distanciaCalculada);
                lugaresFiltrados.add(lugar);
            }
        }
        return lugaresFiltrados;
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en Km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distancia en Km
    }
}