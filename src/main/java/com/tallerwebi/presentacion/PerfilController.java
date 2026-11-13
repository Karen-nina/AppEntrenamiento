package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class PerfilController {

    private final ServicioUsuario servicioUsuario;
    private final ServicioMembresia servicioMembresia;

    private ServicioTipoEntrenamiento servicioTipoEntrenamiento;

    private ServicioAptitudFisica servicioAptitudFisica;


    @Autowired
    public PerfilController(ServicioUsuario servicioUsuario, ServicioMembresia servicioMembresia, ServicioTipoEntrenamiento servicioTipoEntrenamiento,ServicioAptitudFisica servicioAptitudFisica) {
        this.servicioUsuario = servicioUsuario;
        this.servicioMembresia = servicioMembresia;
        this.servicioTipoEntrenamiento = servicioTipoEntrenamiento;
        this.servicioAptitudFisica = servicioAptitudFisica;
    }

    @RequestMapping("/perfil")
    @Transactional
    public ModelAndView irAPerfil(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelMap modelo = new ModelMap();
        String mailLogeado = (String) session.getAttribute("Email");
        if (session != null && session.getAttribute("Email") != null) {
            Usuario usuario = servicioUsuario.buscarPorId((Long) session.getAttribute("id"));
            Membresia membresia = servicioMembresia.membresiasPorId(usuario.getId());
            AptitudFisica aptitudFisica = usuario.getAptitudFisica();
            List<AptitudFisicaTipoEntrenamiento> userEntrenamiento = aptitudFisica.getAptitudFisicaTipoEntrenamientos();
            List<TipoEntrenamiento> tiposEntrenamiento = servicioTipoEntrenamiento.findAll();
            modelo.put("Email", mailLogeado);
            modelo.put("usuario", usuario);
            modelo.put("aptitudFisica", aptitudFisica);
            modelo.put("membresia", membresia);
            modelo.put("userTiposEntrenamiento", userEntrenamiento);
            modelo.put("tipoDeEntrenamientos", tiposEntrenamiento);
            return new ModelAndView("perfil", modelo);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }


    @PostMapping("/guardar-aptitud-fisica")
    @ResponseBody
    public ResponseEntity<?> guardarAptitudFisica(@ModelAttribute AptitudFisica aptitudFisica) {
        try {
            servicioAptitudFisica.update(aptitudFisica);
            return ResponseEntity.ok().body("Datos actualizados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar los datos");
        }
    }

    @PostMapping("/guardar-user-data")
    @ResponseBody
    public ResponseEntity<?> guardarUserData(@ModelAttribute Usuario user) {
        try {
            servicioUsuario.updateData(user);
            return ResponseEntity.ok().body("Datos actualizados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar los datos");
        }
    }

}