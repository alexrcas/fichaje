package net.avantic.story.web.addvacaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public RedirectView handleForm(RedirectAttributes redirectAttributes, AddVacacionesCommand command) {
        try {
            facade.addVacaciones(command);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/listVacaciones");
    }
}
