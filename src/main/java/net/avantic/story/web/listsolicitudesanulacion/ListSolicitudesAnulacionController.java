package net.avantic.story.web.listsolicitudesanulacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/listSolicitudesAnulacion")
public class ListSolicitudesAnulacionController {

    private final ListSolicitudesAnulacionFacade facade;

    @Autowired
    public ListSolicitudesAnulacionController(ListSolicitudesAnulacionFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("username", facade.getAuthenticatedUsername());
        model.addAttribute("vacaciones", facade.listVacaciones());
        return "listSolicitudesAnulacion";
    }

}
