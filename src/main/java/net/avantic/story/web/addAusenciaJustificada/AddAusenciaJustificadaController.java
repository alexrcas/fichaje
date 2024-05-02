package net.avantic.story.web.addAusenciaJustificada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/web/admin/addAusenciaJustificada")
public class AddAusenciaJustificadaController {

    private final AddAusenciaJustificadaFacade facade;

    @Autowired
    public AddAusenciaJustificadaController(AddAusenciaJustificadaFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public RedirectView handleForm(RedirectAttributes redirectAttributes, AddAusenciaJustificadaCommand command) {
        try {
            facade.addAusenciaJustificada(command);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/admin/listAusenciasJustificadas");
    }
}
