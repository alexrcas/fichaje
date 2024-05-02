package net.avantic.story.web.supervisarfichaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/supervisarFichaje")
public class SupervisarFichajeController {

    private final SupervisarFichajeFacade facade;

    @Autowired
    public SupervisarFichajeController(SupervisarFichajeFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model, @RequestParam("idEmpleado") Long idEmpleado) {
        model.addAttribute("username", facade.getAuthenticatedUsername());
        model.addAttribute("empleados", facade.listEmpleados());
        model.addAttribute("semanasJornadas", facade.listJornadas(idEmpleado));

        return "supervisarFichaje";
    }

}
