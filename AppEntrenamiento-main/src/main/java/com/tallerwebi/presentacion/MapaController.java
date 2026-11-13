package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.SearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MapaController {

    private ServicioMapa servicioSearch;
    private ServicioMembresia servicioMembresia;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public MapaController(ServicioMapa servicioSearch, ServicioMembresia servicioMembresia, ServicioUsuario servicioUsuario) {
        this.servicioSearch = servicioSearch;
        this.servicioUsuario = servicioUsuario;
        this.servicioMembresia = servicioMembresia;
    }

    @Transactional
    @GetMapping("/mapaBuscador")
    public ModelAndView irASearch(@RequestParam(value = "tipoActividad", required = false) String tipoActividad,
                                  @RequestParam(value = "distancia", required = false) Double distancia,
                                  HttpServletRequest request) {
        List<Lugar> lugares;
        HttpSession session = request.getSession(false);
        ModelMap modelo = new ModelMap();
        double latitudUsuario;
        double longitudUsuario;

        try {
            if (session != null && session.getAttribute("Email") != null) {
                modelo.put("Email", session.getAttribute("Email"));
                modelo.put("id", session.getAttribute("id"));
                Usuario usuario = servicioUsuario.buscarPorId((Long) session.getAttribute("id"));

                modelo.put("usuario", usuario);
                latitudUsuario = usuario.getLatitud();
                longitudUsuario = usuario.getLongitud();
                modelo.put("latitudUsuario", latitudUsuario);
                modelo.put("longitudUsuario", longitudUsuario);

               Membresia membresia = servicioMembresia.membresiasPorId(usuario.getId());
//                if (membresia == null || "GRATUITO".equals(membresia.getTipo())) {
//                    modelo.put("error", "No tienes acceso a esta sección. Actualice su Membresia");
//                    return new ModelAndView("datos", modelo);
//                }
//                if (membresia != null && membresia.getFechaFin().isBefore(LocalDate.now())) {
//                    modelo.put("error", "Su membresía ha finalizado. Actualice su Membresía");
//                    return new ModelAndView("tipsYNutricion", modelo);
//                }

            } else {
                latitudUsuario = -34.74973643128108;
                longitudUsuario = -58.571734784656066;
                modelo.put("latitudUsuario", latitudUsuario);
                modelo.put("longitudUsuario", longitudUsuario);
            }


            if (tipoActividad == null || tipoActividad.isEmpty()) {
                lugares = servicioSearch.buscarSitios(latitudUsuario, longitudUsuario);
            } else {
                Long idActividad = Long.parseLong(tipoActividad);
                lugares = servicioSearch.buscarLugaresPorTipoActividad(idActividad, latitudUsuario, longitudUsuario);
            }

            if (distancia != null) {
                lugares = servicioSearch.filtrarLugaresPorDistancia(lugares, latitudUsuario, longitudUsuario, distancia);
            }
            List<Double> array = new ArrayList<>();
            if (lugares.isEmpty()) {
                modelo.put("error", "No se encontraron lugares para los filtros seleccionados.");
            }else{
                array = obtenerLongitudLatitudLugares(lugares);
                modelo.put("longitudLatitud", array);
            }

            modelo.put("lugares", lugares);

        } catch (SearchException e) {
            modelo.put("error", "Error al buscar lugares: ");
        }

        return new ModelAndView("mapaBuscador", modelo);
    }

    private List<Double> obtenerLongitudLatitudLugares(List<Lugar> lugares){
        List<Double> array = new ArrayList<>();
        lugares.forEach( l ->{
            array.add(l.getLongitud());
            array.add(l.getLatitud());
        });
        return array;
    }

    @PostMapping("/actualizarUbicacion")
    public ResponseEntity<String> actualizarUbicacion(@RequestParam("latitud") double latitud,
                                                      @RequestParam("longitud") double longitud,
                                                      HttpSession session) {
        if (session != null && session.getAttribute("id") != null) {
            Long usuarioId = (Long) session.getAttribute("id");
            try {
                Usuario usuario = servicioUsuario.buscarPorId(usuarioId);
                usuario.setLatitud(latitud);
                usuario.setLongitud(longitud);
                servicioUsuario.updateData(usuario);
                return ResponseEntity.ok("Ubicación actualizada exitosamente.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al actualizar la ubicación: ");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Usuario no autenticado");
        }
        //return new ModelAndView("redirect:/home", modelo);
    }
}
