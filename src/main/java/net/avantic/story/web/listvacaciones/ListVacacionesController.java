package net.avantic.story.web.listvacaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/listVacaciones")
public class ListVacacionesController {

    private final ListVacacionesFacade facade;

    @Autowired
    public ListVacacionesController(ListVacacionesFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vacaciones", facade.listVacaciones());
        return "listVacaciones";
    }

}
