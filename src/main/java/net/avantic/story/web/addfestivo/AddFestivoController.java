package net.avantic.story.web.addfestivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/web/addFestivo")
public class AddFestivoController {

    private final AddFestivoFacade facade;

    @Autowired
    public AddFestivoController(AddFestivoFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public RedirectView handleForm(RedirectAttributes redirectAttributes, AddFestivoCommand command) {
        try {
            facade.addFestivo(command);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/listFestivos");
    }
}
