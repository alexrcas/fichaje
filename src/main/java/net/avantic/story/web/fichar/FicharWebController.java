package net.avantic.story.web.fichar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/web/fichar")
public class FicharWebController {

    private final FicharWebFacade facade;

    @Autowired
    public FicharWebController(FicharWebFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public RedirectView handleForm(FicharWebCommand command) {
        facade.fichar(command);
        return new RedirectView("/listFichajes");
    }
}
