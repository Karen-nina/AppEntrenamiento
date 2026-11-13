package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosIncompletosLogin;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;
    private RepositorioUsuario repositorioUsuario;
    private ServicioRutina servicioRutina;


    private ServicioTipoEntrenamiento servicioTipoEntrenamiento;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin, RepositorioUsuario repositorioUsuario, ServicioRutina servicioRutina,ServicioTipoEntrenamiento servicioTipoEntrenamiento){
        this.servicioLogin = servicioLogin;
        this.repositorioUsuario = repositorioUsuario;
        this.servicioRutina = servicioRutina;
        this.servicioTipoEntrenamiento = servicioTipoEntrenamiento;
    }

    @RequestMapping("/login")
    public ModelAndView irALogin() {
        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", modelo);
    }
    @Transactional
    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
        if (usuarioBuscado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("ROL", "ADMIN");
            session.setAttribute("Email", datosLogin.getEmail());
            session.setAttribute("id", usuarioBuscado.getId());
            return new ModelAndView("redirect:/home");
        } else {
            model.put("error", "Usuario o clave incorrecta");
            return new ModelAndView("login", model);
        }
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
        ModelMap model = new ModelMap();
        try{
            servicioLogin.registrar(usuario);
        } catch (UsuarioExistente e){
            model.put("error", "El usuario ya existe");
            return new ModelAndView("nuevo-usuario", model);
        } catch (DatosIncompletosLogin e){
            model.put("error", "Faltan datos para Registrar / Asegurate de elegir tu lugar de residencia");
            return new ModelAndView("nuevo-usuario", model);
        }
        List<TipoEntrenamiento> tiposEntrenamiento = servicioTipoEntrenamiento.findAll();
        model.put("tipoDeEntrenamientos", tiposEntrenamiento);
        model.put("aptitudFisica", new AptitudFisica());
        model.put("usuario",usuario);
        return new ModelAndView("formularioAptitudFisica",model);
    }

    @RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelMap model = new ModelMap();
        model.put("usuario", new Usuario());
        return new ModelAndView("nuevo-usuario", model);
    }
    @Transactional
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        ModelMap model = new ModelMap();
        if (session != null && session.getAttribute("Email") != null) {
           model.put("Email",session.getAttribute("Email") );
           model.put("id",session.getAttribute("id") );
         Usuario usuario = repositorioUsuario.buscarPorId((Long)session.getAttribute("id"));
         List<RutinaSemanal>  rutinasSemanales = servicioRutina.buscarPorIdDeUsuario(usuario.getId());
            System.out.println(usuario.getRutinaSemanal().size());
            model.put("rutinasSemanales",rutinasSemanales);
            model.put("usuario",usuario);
            return new ModelAndView("home",model);
        }else{
            return new ModelAndView("redirect:/login");
        }
    }
    @RequestMapping(path ="/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }
}

