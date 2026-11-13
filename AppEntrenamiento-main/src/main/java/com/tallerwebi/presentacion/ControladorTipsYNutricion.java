package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.NoHayArticulosDeEseTipo;
import com.tallerwebi.dominio.excepcion.NoHayInformacionDelArticulo;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@Transactional
public class ControladorTipsYNutricion {
    ServicioLogin servicioLogin;
    ServicioTipsYNutricion servicioTipsYNutricion;
    ServicioMembresia servicioMembresia;
    @Autowired
    public ControladorTipsYNutricion(ServicioLogin servicioLogin, ServicioTipsYNutricion servicioTipsYNutricion,ServicioMembresia servicioMembresia) {
        this.servicioLogin = servicioLogin;
        this.servicioTipsYNutricion = servicioTipsYNutricion;
        this.servicioMembresia = servicioMembresia;
    }

    @RequestMapping("/tipsYNutricion")

    public ModelAndView vistaTipsYNutricion(@RequestParam(defaultValue = "todos") String tipo,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "12") int size,
                                            HttpServletRequest request) throws UsuarioInexistenteException {
        HttpSession session = request.getSession(false);
        ModelMap modelo = new ModelMap();
        String mailLogeado = (String) session.getAttribute("Email");
        if (session != null && mailLogeado != null) {
            try {
                Usuario usuario = servicioLogin.buscarPorMail(mailLogeado);

                List<Articulo> listaArticulos = servicioTipsYNutricion.buscarTipsPorTipoDeEntrenamiento(tipo,page,size);
                long totalArticulos = servicioTipsYNutricion.contarTotalDeArticulos();
                int totalPages = (int) Math.ceil((double) totalArticulos / size);
                modelo.put("totalPages", totalPages);
                modelo.put("currentPage",page);
                modelo.put("Email",mailLogeado);
                Membresia membresia = servicioMembresia.membresiasPorId(usuario.getId());
                if (membresia == null || "GRATUITO".equals(membresia.getTipo()) || "INTERMEDIO".equals(membresia.getTipo())) {
                    modelo.put("error", "No tienes acceso a esta sección.");
                    return new ModelAndView("tipsYNutricion", modelo);
                }
                if (membresia != null && membresia.getFechaFin().isBefore(LocalDate.now())) {
                    modelo.put("error", "Su membresía ha finalizado. Actualice su Membresía");
                    return new ModelAndView("tipsYNutricion", modelo);
                }
                modelo.put("articulos", listaArticulos);
                modelo.put("tipoEntrenamiento",usuario.getAptitudFisica().getTiposEntrenamiento());


            } catch (NoHayArticulosDeEseTipo e) {
                modelo.put("error", "No hay articulos");
                return new ModelAndView("tipsYNutricion", modelo);
            }
        } else {
            return new ModelAndView("redirect:/login");
        }
        modelo.put("Email", mailLogeado);
        return new ModelAndView("tipsYNutricion", modelo);
    }
    @RequestMapping("/tipsYNutricion/{id}")
    @ResponseBody
    public Articulo  obtenerDetalleArticulo(@PathVariable Long id) throws NoHayInformacionDelArticulo {
        System.out.println(id);
        Articulo articulo = servicioTipsYNutricion.getArticuloPorId(id);
        if (articulo != null) {
            return articulo;
        } else {
            return null;
        }
    }
}
