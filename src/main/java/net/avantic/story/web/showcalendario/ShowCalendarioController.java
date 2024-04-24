package net.avantic.story.web.showcalendario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showCalendar")
public class ShowCalendarioController {

    private final ShowCalendarioFacade facade;

    @Autowired
    public ShowCalendarioController(ShowCalendarioFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute(facade.listVacaciones());
        model.addAttribute(facade.listFestivos());
        return "showCalendar";
    }
}
