package com.tallerwebi.presentacion;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.MembresiaNoEncontrada;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ControladorMembresia {

    private ServicioLogin servicioLogin;

    private ServicioMembresia servicioMembresia;

    private ServicioRutina servicioRutina;
    private ServicioMercadoPago servicioMercadoPago;
    private HttpSession session;
    @Autowired
    public ControladorMembresia(ServicioLogin servicioLogin, ServicioMembresia servicioMembresia,ServicioRutina servicioRutina, ServicioMercadoPago servicioMercadoPago,HttpSession session){
        this.servicioLogin = servicioLogin;
        this.servicioMembresia = servicioMembresia;
        this.servicioRutina = servicioRutina;
        this.servicioMercadoPago = servicioMercadoPago;
        this.session = session;
    }
    @Transactional
    @RequestMapping(path = "/asignarMembresia", method = RequestMethod.POST)
    public ModelAndView asignarMembresia(@RequestParam String tipo, @RequestParam String email, @RequestParam int duracion, HttpServletRequest request) throws UsuarioInexistenteException {
        ModelMap modelo = new ModelMap();
        try {
            HttpSession session = request.getSession(false);
            Usuario usuario = servicioLogin.buscarPorMail(email);
            // Verificar si el usuario ya tiene una membresía activa -> para que el pago se haga
           Membresia membresiaExistente = servicioMembresia.buscarMembresiaPendientePorUsuario(usuario);
            if (membresiaExistente != null) {
             servicioMembresia.eliminarPorUsuario(usuario);
            }
            //  valida la membresia del usuario -> si existe una la elimina -> Se usa para Actualizar.
            validarMembresia(usuario.getId());

            Membresia membresia = new Membresia();
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaFutura = fechaActual.plusMonths(duracion);
            membresia.setFechaFin(fechaFutura);
            membresia.setFechaInicio(fechaActual);
            membresia.setDuracion(duracion);
            membresia.setTipo(tipo);
             membresia.setUsuario(usuario);
             switch (tipo){
                 case "INTERMEDIO":
             membresia.setValor(1000.0);
                     break;
                 case "PREMIUM":
                     membresia.setValor(2000.0);
                     break;
                  default:
                      membresia.setValor(0.0);
                     break;
             }
             double valorMembresia =   membresia.getValor();
             double total = valorMembresia * duracion;
            servicioMembresia.crearMembresia(membresia);
            if (tipo.equalsIgnoreCase("GRATUITO")){
                membresia.setEstado("ACTIVA");
                servicioMembresia.actualizarMembresia(membresia);
                List<RutinaSemanal> rutinaSemanal = servicioRutina.generarRutinaSemanal(membresia.getUsuario());
                session.setAttribute("membresia", membresia);
                session.setAttribute("usuario", membresia.getUsuario());
                session.setAttribute("rutinaSemanal", rutinaSemanal);
                return new ModelAndView("redirect:/home");
            }
            // Mando en session el id de la membresia creada.
            session.setAttribute("idMembresia",membresia.getId());
            DatosPreferencia preference = servicioMercadoPago.crearPreferenciaPago(total);
            modelo.put("idPreferencia", preference);
            return new ModelAndView("redirect:" + preference.urlCheckout, modelo);
        } catch (UsuarioInexistenteException ex) {
            modelo.put("error", "Usuario no encontrado");
            return new ModelAndView("home", modelo);
        } catch (MPException e) {
            e.printStackTrace();
            modelo.put("error",  e.getMessage());
            return new ModelAndView("home", modelo);
        } catch (MPApiException e) {
            e.printStackTrace();
            modelo.put("error", e.getMessage());
            return new ModelAndView("home", modelo);
        } catch (MembresiaNoEncontrada e) {
            throw new RuntimeException(e);
        }
    }
    //Aca te redirecciona MercadoPago
    @Transactional
    @RequestMapping(path = "/validar-pago", method = RequestMethod.GET)
    public ModelAndView validarPago(@RequestParam("status") String status) throws MembresiaNoEncontrada {
        ModelMap modelo = new ModelMap();
        Long membresiaId = (Long) session.getAttribute("idMembresia");
        // Si es Exitoso, genero la rutina y mantengo la Membresia.
        if (status.equals("approved")) {
            Membresia membresia = servicioMembresia.buscarPorId(membresiaId);
            // Activar la membresía solo si el pago fue aprobado
            membresia.setEstado("ACTIVA");
            servicioMembresia.actualizarMembresia(membresia);

            List<RutinaSemanal> rutinaSemanal = servicioRutina.generarRutinaSemanal(membresia.getUsuario());
            session.setAttribute("membresia", membresia);
            session.removeAttribute("idMembresia");
            session.setAttribute("usuario", membresia.getUsuario());
            session.setAttribute("rutinaSemanal", rutinaSemanal);
        } else {
            //Si se genera algun error al procesar el pago, se Elimina la membresia y se envia al front un msj de error.
            servicioMembresia.eliminarPorId(membresiaId);
            modelo.put("error", "Error al procesar el pago.");
            return new ModelAndView("redirect:/home", modelo);
        }
        return new ModelAndView("redirect:/home");
    }
    public void validarMembresia(Long UsuarioId) throws MembresiaNoEncontrada {
        Membresia membresia = this.servicioMembresia.membresiasPorId(UsuarioId);
        if (membresia != null) {
            this.servicioMembresia.eliminarPorId(membresia.getId());
        }
    }

}
