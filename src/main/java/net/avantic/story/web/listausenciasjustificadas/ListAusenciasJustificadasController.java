package net.avantic.story.web.listausenciasjustificadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/listAusenciasJustificadas")
public class ListAusenciasJustificadasController {

    private final ListAusenciasJustificadasFacade facade;

    @Autowired
    public ListAusenciasJustificadasController(ListAusenciasJustificadasFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("ausencias", facade.listAusencias());
        return "listAusenciasJustificadas";
    }

}
