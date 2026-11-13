package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosMalIngresadosException;
import com.tallerwebi.dominio.excepcion.esMenorDeEdadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorAptitudFisica {

    private final ServicioAptitudFisica servicioAptitudFisica;
    private final RepositorioUsuario repositorioUsuario;
    @Autowired
    public ControladorAptitudFisica(ServicioAptitudFisica servicioAptitudFisica, RepositorioUsuario repositorioUsuario) {
        this.servicioAptitudFisica = servicioAptitudFisica;
        this.repositorioUsuario = repositorioUsuario;
    }

    @RequestMapping("/formularioAptitudFisica")
    public ModelAndView irAFormulario() {
        ModelMap modelo = new ModelMap();
        modelo.put("aptitudFisica", new AptitudFisica());
        return new ModelAndView("formularioAptitudFisica", modelo);
    }

    @RequestMapping(path = "/{id}/guardar-aptitud-fisica", method = RequestMethod.POST)
    @Transactional
    public ModelAndView procesarFormulario(@PathVariable Long id, @ModelAttribute("aptitudFisica") AptitudFisica aptitudFisica, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            Usuario usuario = repositorioUsuario.buscarPorId(id);
            if (usuario == null) {
                return registroFallido(model, "Usuario no encontrado");
            }

            if (aptitudFisica == null ||aptitudFisica.getAltura() == 0.0 || aptitudFisica.getPeso() == 0.0 || aptitudFisica.getHorasEntrenamiento() == 0 || aptitudFisica.getAptitudFisicaTipoEntrenamientos().isEmpty()){
                return registroFallido(model, "Todos los campos deben estar completos");
            }

            aptitudFisica.setUsuario(usuario);

            List<TipoEntrenamiento> tiposEntrenamiento = new ArrayList<>();
            for (AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento : aptitudFisica.getAptitudFisicaTipoEntrenamientos()) {
                tiposEntrenamiento.add(aptitudFisicaTipoEntrenamiento.getTipoEntrenamiento());
            }

            List<Long> dias = new ArrayList<>();
            for (AptitudFisicaTipoEntrenamiento aptitudFisicaTipoEntrenamiento : aptitudFisica.getAptitudFisicaTipoEntrenamientos()) {
                dias.add(aptitudFisicaTipoEntrenamiento.getDias());
            }

            servicioAptitudFisica.registrarDatos(aptitudFisica, tiposEntrenamiento, dias);

            usuario.setAptitudFisica(aptitudFisica);
            repositorioUsuario.guardar(usuario);

            return registroExitoso(model);
        } catch (DatosMalIngresadosException ex) {
            return registroFallido(model, "Faltan Datos");
        } catch (esMenorDeEdadException ex) {
            return registroFallido(model, "Tiene menos de 18 Años");
        } catch (Exception ex) {
            return registroFallido(model, "Error al procesar la solicitud: " + ex.getMessage());
        }
    }
    private ModelAndView registroFallido(ModelMap model, String mensaje) {
        model.put("error", mensaje);
        return new ModelAndView("formularioAptitudFisica", model);
    }

    private ModelAndView registroExitoso(ModelMap model) {

        return new ModelAndView("redirect:/home");
    }
}