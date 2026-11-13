package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Articulo;
import com.tallerwebi.dominio.RepositorioArticulo;
import com.tallerwebi.dominio.ServicioTipsYNutricion;
import com.tallerwebi.dominio.excepcion.NoHayArticulosDeEseTipo;
import com.tallerwebi.dominio.excepcion.NoHayInformacionDelArticulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioTipsYNutricionImp implements ServicioTipsYNutricion {
    @Autowired
    RepositorioArticulo repositorioArticulo;
    @Autowired
    public ServicioTipsYNutricionImp(RepositorioArticulo repositorioArticulo){
        this.repositorioArticulo = repositorioArticulo;
    }
    @Override
    public List<Articulo> buscarTipsPorTipoDeEntrenamiento(String tipoEntrenamiento, int page, int size) throws NoHayArticulosDeEseTipo {
        if (repositorioArticulo.buscarPorTipoEntrenamiento(tipoEntrenamiento) == null && !"todos".equalsIgnoreCase(tipoEntrenamiento)) {
            throw new NoHayArticulosDeEseTipo();
        } else {
            List<Articulo> articulos;
            if ("todos".equalsIgnoreCase(tipoEntrenamiento)) {
                articulos = repositorioArticulo.todosLosArticulos(page, size);
            } else {
                // Obtener artículos por tipo de entrenamiento
                articulos = repositorioArticulo.buscarPorTipoEntrenamiento(tipoEntrenamiento);
            }
            return articulos;
        }
    }


    @Override
    public Articulo getArticuloPorId(Long id) throws NoHayInformacionDelArticulo {
        if (repositorioArticulo.getPorId(id) == null){
            throw new NoHayInformacionDelArticulo();
        }else{
            return repositorioArticulo.getPorId(id);
        }
    }

    @Override
    public Long contarTotalDeArticulos() {
        return repositorioArticulo.contarTotalArticulos();
    }

}
