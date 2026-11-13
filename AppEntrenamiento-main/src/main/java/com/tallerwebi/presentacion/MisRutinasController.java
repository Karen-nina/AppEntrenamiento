package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MisRutinasController {

    private ServicioRutina servicioRutina;
    private RepositorioRutina repositorioRutina;

    @Autowired
    public MisRutinasController(ServicioRutina servicioRutina, RepositorioRutina repositorioRutina){
        this.servicioRutina = servicioRutina;
        this.repositorioRutina = repositorioRutina;
    }

    @RequestMapping("/misRutinas")
    public static ModelAndView verMisRutinas(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("Email") != null) {
            ModelMap modelo = new ModelMap();
            modelo.put("detalleRutina", new DetalleRutina());
            modelo.put("Email", session.getAttribute("Email"));
            return new ModelAndView("misRutinas", modelo);
        } else {
            return new ModelAndView("redirect:/login");

        }
    }
}

//    @RequestMapping("/routines-update", method = RequestMethod.POST)
//    public ModelAndView actualizarMisRutinas() {
//        ModelMap modelo = new ModelMap();
//        modelo.put("detalleRutina", new DetalleRutina());
//        return new ModelAndView("redirect:/misRutinas", modelo);
//    }

