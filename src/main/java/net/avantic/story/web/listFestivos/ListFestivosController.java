package net.avantic.story.web.listFestivos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listFestivos")
public class ListFestivosController {

    private final ListFestivosFacade facade;

    @Autowired
    public ListFestivosController(ListFestivosFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vacaciones", facade.listVacaciones());
        return "listFestivos";
    }

}
