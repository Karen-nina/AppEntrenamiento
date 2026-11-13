package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.ErrorPesoRegistroIsEmpty;
import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorDatos {

    private final ServicioUsuario servicioUsuario;
    private final ServicioPeso servicioPeso;
    private final ServicioMembresia servicioMembresia;

    private final ServicioRutina servicioRutina;

    @Autowired
    public ControladorDatos(ServicioUsuario servicioUsuario, ServicioPeso servicioPeso, ServicioMembresia servicioMembresia,ServicioRutina servicioRutina) {
        this.servicioUsuario = servicioUsuario;
        this.servicioPeso = servicioPeso;
        this.servicioMembresia = servicioMembresia;
        this.servicioRutina = servicioRutina;
    }

    @Transactional
    @RequestMapping("/datos")
    public ModelAndView mostrarDatos(HttpServletRequest request)  {
        HttpSession session = request.getSession(false);
        ModelMap modelo = new ModelMap();

        if (session != null && session.getAttribute("Email") != null) {
            modelo.put("Email", session.getAttribute("Email"));
            modelo.put("id", session.getAttribute("id"));
            Usuario usuario = servicioUsuario.buscarPorId((Long) session.getAttribute("id"));
            ArrayList pesosRegistro = null;
            try {
                pesosRegistro = servicioPeso.obtenerPesosPorAnio(usuario.getId());
            } catch (ErrorPesoRegistroIsEmpty e) {
                modelo.put("error", "No hay datos para mostrar");
            }
            Membresia membresia = servicioMembresia.membresiasPorId(usuario.getId());

            if (membresia == null || "GRATUITO".equals(membresia.getTipo()) || "INTERMEDIO".equals(membresia.getTipo())) {
                modelo.put("error", "No tienes acceso a esta sección. Actualice su Membresia");
                return new ModelAndView("datos", modelo);
            }
            if (membresia != null && membresia.getFechaFin().isBefore(LocalDate.now())) {
                modelo.put("error", "Su membresía ha finalizado. Actualice su Membresía");
                return new ModelAndView("datos", modelo);
            }

            DatosDiasYEjercicios datos = null;
            try {
                datos = servicioRutina.procesarRutinas(usuario.getId());
            } catch (RutinaSemanalVacia e) {
                modelo.put("error", "No hay datos para mostrar");
            }
            modelo.put("registroPeso", pesosRegistro);
            modelo.put("usuario", usuario);
            modelo.put("membresia", membresia);
            modelo.put("dias", datos.getDiasList());
            modelo.put("ejercicios", datos.getEjerciciosList());
            modelo.put("labelDias", datos.getLabelDias());
            modelo.put("labelEjercicios", datos.getLabelEjercicios());
            modelo.put("datosPeso", new DatosPeso());
            return new ModelAndView("datos", modelo);
        }

        return new ModelAndView("redirect:/login");
    }

    @Transactional
    @RequestMapping(value = "/cargar-peso", method = RequestMethod.POST)
    public ModelAndView postPeso(@ModelAttribute("datosPeso") DatosPeso datosPeso, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Email") != null) {
            Long usuarioId = (Long) session.getAttribute("id");
            Usuario usuario = servicioUsuario.buscarPorId(usuarioId);
            servicioPeso.postPeso(usuario, datosPeso);
            return new ModelAndView("redirect:/datos?id=" + usuarioId);
        }

        return new ModelAndView("redirect:/login");  
    }

    @Transactional
    @RequestMapping(value = "/filtrar-datos", method = RequestMethod.GET)
    @ResponseBody
    public List<Double> filtrarDatosPorMes(@RequestParam("mes") int mes, HttpServletRequest request) throws ErrorPesoRegistroIsEmpty {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            Long usuarioId = (Long) session.getAttribute("id");
            if (mes == 0) {
                return servicioPeso.obtenerPesosPorAnio(usuarioId);
            } else {
                return servicioPeso.obtenerPesosPorMes(usuarioId, mes);
            }
        }
        return new ArrayList<>();
    }

    @Transactional
    @RequestMapping(value = "/limpiar-filtro", method = RequestMethod.GET)
    @ResponseBody
    public List<Double> limpiarFiltro(HttpServletRequest request) throws ErrorPesoRegistroIsEmpty {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            Long usuarioId = (Long) session.getAttribute("id");
            return servicioPeso.obtenerPesosPorAnio(usuarioId);
        }
        return new ArrayList<>();
    }

}
