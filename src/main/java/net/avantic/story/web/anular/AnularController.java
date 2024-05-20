package net.avantic.story.web.anular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/web/admin/anular")
public class AnularController {

    private final AnularFacade facade;

    @Autowired
    public AnularController(AnularFacade facade) {
        this.facade = facade;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView handleForm(RedirectAttributes redirectAttributes, @RequestParam("idFichaje") Long idFichaje) {
        try {
            AnularCommand command = new AnularCommand(idFichaje);
            facade.anular(command);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/listFichajes");
    }
}
