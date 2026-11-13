package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.NoHayArticulosDeEseTipo;
import com.tallerwebi.dominio.excepcion.NoHayInformacionDelArticulo;
import com.tallerwebi.infraestructura.ServicioTipsYNutricionImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioTipsYNutricionTest {

    private ServicioTipsYNutricion servicioTipsYNutricion;
    private RepositorioArticulo repositorioArticulo;

    @BeforeEach
    public void init() {
        repositorioArticulo = mock(RepositorioArticulo.class);
        servicioTipsYNutricion = new ServicioTipsYNutricionImp(repositorioArticulo);
    }

    @Test
    public void buscarTipsPorTipoDeEntrenamientoExistentes() throws NoHayArticulosDeEseTipo {
        // Arrange
        String tipoEntrenamiento = "Musculacion";
        List<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo());
        System.out.println(articulos);
        when(repositorioArticulo.buscarPorTipoEntrenamiento(tipoEntrenamiento)).thenReturn(articulos);

        // Act
        List<Articulo> resultado = servicioTipsYNutricion.buscarTipsPorTipoDeEntrenamiento(tipoEntrenamiento,0,10);

        // Assert
        assertNotNull(resultado);
        assertEquals(articulos, resultado);
        verify(repositorioArticulo, times(2)).buscarPorTipoEntrenamiento(tipoEntrenamiento);
    }

    @Test
    public void buscarTipsDeEntrenamientoPeroQueNoExistanArticulos() {
        // Given tengo un tipo de entrenamiento
        String tipoEntrenamiento = "Boxeo";
        when(repositorioArticulo.buscarPorTipoEntrenamiento(tipoEntrenamiento)).thenReturn(null);

        // when no existe ese entrenamiento
        assertThrows(NoHayArticulosDeEseTipo.class, () -> {
            servicioTipsYNutricion.buscarTipsPorTipoDeEntrenamiento(tipoEntrenamiento,0,10);
        });
        verify(repositorioArticulo, times(1)).buscarPorTipoEntrenamiento(tipoEntrenamiento);
    }

    @Test
    public void buscoUnArticulosPorSuIdYExiste() throws NoHayInformacionDelArticulo {
        // given  tengo un tengo el id de un articulo
        Long id = 1L;
        Articulo articuloMock = new Articulo(); // Crea un artículo de prueba
        when(repositorioArticulo.getPorId(id)).thenReturn(articuloMock);

        // when Busco el articulo
        Articulo resultado = servicioTipsYNutricion.getArticuloPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(articuloMock, resultado);
        verify(repositorioArticulo, times(2)).getPorId(id);
    }

    @Test
    public void cuandoBuscoUnArticuloPorSuId() {
       //given busco un articulo por su id
        Long id = 2L;
        when(repositorioArticulo.getPorId(id)).thenReturn(null);


        assertThrows(NoHayInformacionDelArticulo.class, () -> {
            servicioTipsYNutricion.getArticuloPorId(id);
        });
        verify(repositorioArticulo, times(1)).getPorId(id);
    }
}
