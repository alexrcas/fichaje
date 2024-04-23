package net.avantic.story.web.showcalendario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showCalendar")
public class ShowCalendarioController {

    @GetMapping
    public String show(Model model) {
        return "showCalendar";
    }
}
