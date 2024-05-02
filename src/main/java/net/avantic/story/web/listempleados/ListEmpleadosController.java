package net.avantic.story.web.listempleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/listEmpleados")
public class ListEmpleadosController {

    private final ListEmpleadosFacade facade;

    @Autowired
    public ListEmpleadosController(ListEmpleadosFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("username", facade.getAuthenticatedUsername());
        model.addAttribute("empleados", facade.listEmpleados());
        return "listEmpleados";
    }

}
