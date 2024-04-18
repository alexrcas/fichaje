package net.avantic.story.ficharextemporaneo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ficharExtemporaneo")
public class FicharExtemporaneoController {

    private final FicharExtemporaneoFacade facade;

    @Autowired
    public FicharExtemporaneoController(FicharExtemporaneoFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    ResponseEntity fichar(@RequestBody FicharExtemporaneoRequestBody requestBody) {
        facade.fichar(requestBody.getIdEmpleado(), requestBody.getTipoFichaje(), requestBody.getHora());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
