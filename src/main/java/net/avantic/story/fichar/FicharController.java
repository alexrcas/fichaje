package net.avantic.story.fichar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fichar")
public class FicharController {

    private final FicharFacade facade;

    @Autowired
    public FicharController(FicharFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    ResponseEntity fichar(@RequestBody FicharRequestBody requestBody) {
        facade.fichar(requestBody.getIdEmpleado(), requestBody.getTipoFichaje());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
