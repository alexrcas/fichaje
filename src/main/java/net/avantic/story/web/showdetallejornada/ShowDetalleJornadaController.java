package net.avantic.story.web.showdetallejornada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showDetalleJornada")
public class ShowDetalleJornadaController {

    private final ShowDetalleJornadaFacade facade;

    @Autowired
    public ShowDetalleJornadaController(ShowDetalleJornadaFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/{id}")
    public String list(@PathVariable("id") Long idJornada, Model model) {
        model.addAttribute("fichajes", facade.listFichajesJornada(idJornada));
        model.addAttribute("validacion", facade.listValidacionesJornada(idJornada));
        model.addAttribute("dia", facade.getFechaJornada(idJornada));
        model.addAttribute("computo", facade.getComputo(idJornada));
        return "showDetalleFichajeModal";
    }

}
