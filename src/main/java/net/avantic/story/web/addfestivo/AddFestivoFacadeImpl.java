package net.avantic.story.web.addfestivo;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import net.avantic.domain.dao.*;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.DiaLibre;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.Festivo;
import net.avantic.domain.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AddFestivoFacadeImpl implements AddFestivoFacade {


    private final DiaRepository diaRepository;
    private final DiaService diaService;
    private final FestivoRepository festivoRepository;

    @Autowired
    public AddFestivoFacadeImpl(DiaRepository diaRepository,
                                DiaService diaService,
                                FestivoRepository festivoRepository) {
        this.diaRepository = diaRepository;
        this.diaService = diaService;
        this.festivoRepository = festivoRepository;
    }

    @Transactional
    @Override
    public void addFestivo(AddFestivoCommand command) throws Exception {

        assertCommand(command);

        Dia dia = diaService.getByFecha(command.getFecha());
        dia.setFestivo(true);
        diaRepository.save(dia);

        Festivo festivo = new Festivo(dia, command.getMotivo());
        festivoRepository.save(festivo);
    }


    private void assertCommand(AddFestivoCommand command) throws Exception {

        if (command.getFecha() == null) {
            throw new RuntimeException("No se ha especificado una fecha");
        }

        if (StringUtils.isBlank(command.getMotivo())) {
            throw new RuntimeException("No se ha especificado un motivo");
        }

        Dia dia = diaService.getByFecha(command.getFecha());
        if (dia.isFestivo()) {
            throw new RuntimeException("El día especificado ya está marcado como festivo");
        }

        if (dia.isFinSemana()) {
            throw new RuntimeException("No puede marcarse como festivo un día de fin de semana");
        }
    }



}
