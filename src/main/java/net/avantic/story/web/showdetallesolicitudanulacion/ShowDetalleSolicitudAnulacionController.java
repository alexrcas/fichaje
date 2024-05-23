package net.avantic.story.web.showdetallesolicitudanulacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/showDetalleSolicitudAnulacion")
public class ShowDetalleSolicitudAnulacionController {

    private final ShowDetalleSolicitudAnulacionFacade facade;

    @Autowired
    public ShowDetalleSolicitudAnulacionController(ShowDetalleSolicitudAnulacionFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/{idFichaje}")
    public String list(@PathVariable("idFichaje") Long idFichaje, Model model) {
        model.addAttribute("username", facade.getUsernameEmpleadoFichaje(idFichaje));
        model.addAttribute("fichajes", facade.listFichajesJornada(idFichaje));
        model.addAttribute("validacion", facade.listValidacionesJornada(idFichaje));
        model.addAttribute("dia", facade.getFechaJornada(idFichaje));
        model.addAttribute("computo", facade.getComputo(idFichaje));
        model.addAttribute("ausenciasJustificadas", facade.listAusenciasJustificadas(idFichaje));
        model.addAttribute("idFichaje", idFichaje);

        return "showDetalleSolicitudAnulacionModal";
    }

}
