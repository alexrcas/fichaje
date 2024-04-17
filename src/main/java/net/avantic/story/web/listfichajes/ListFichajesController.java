package net.avantic.story.web.listfichajes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listFichajes")
public class ListFichajesController {

    private final ListFichajesFacade facade;

    @Autowired
    public ListFichajesController(ListFichajesFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("dias", facade.listDias());
        model.addAttribute("jornadas", facade.listJornadas());
        return "listFichajes";
    }

}
