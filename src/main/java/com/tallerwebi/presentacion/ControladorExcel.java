package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.RepositorioRutinaSemanal;
import com.tallerwebi.dominio.RutinaSemanal;
import com.tallerwebi.dominio.excepcion.RutinaSemanalVacia;
import com.tallerwebi.infraestructura.ServicioExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
public class ControladorExcel {
    @Autowired
    ServicioExcel servicioExcel;
    @Autowired
    RepositorioRutinaSemanal respositorioRutinaSemanal;
    @Transactional
    @RequestMapping("/generarRutina")
    public ModelAndView generarRutina(@RequestParam Long idUsuario, HttpServletResponse response, HttpServletRequest request) throws RutinaSemanalVacia {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=rutinaExportar.xlsx");
          List<RutinaSemanal> rutinasSemanal=  respositorioRutinaSemanal.buscarPorIdDeUsuario(idUsuario);
            servicioExcel.getRutinaExcel(rutinasSemanal, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            // Manejar adecuadamente la excepción, podrías devolver un mensaje de error al cliente
            e.printStackTrace();
        }

        // Redireccionar a la página de inicio (o cualquier otra página)
        String contextPath = request.getContextPath();
        return new ModelAndView("redirect:/home");
    }
}
