package net.avantic.story.web.addvacaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/web/addVacaciones")
public class AddVacacionesController {

    private final AddVacacionesFacade facade;

    @Autowired
    public AddVacacionesController(AddVacacionesFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public RedirectView handleForm(AddVacacionesCommand command) {
        facade.addVacaciones(command);
        return new RedirectView("/listVacaciones");
    }
}
