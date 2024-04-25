package net.avantic.story.web.showcalendario;

import net.avantic.domain.dao.DiaLibreRepository;
import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.dao.VacacionesRepository;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.VacacionesDto;
import net.avantic.domain.model.dto.factory.VacacionesDtoFactory;
import net.avantic.domain.service.FechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowCalendarioFacadeImpl implements ShowCalendarioFacade {

    private final VacacionesRepository vacacionesRepository;
    private final DiaRepository diaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final VacacionesDtoFactory vacacionesDtoFactory;
    private final FechaService fechaService;

    @Autowired
    public ShowCalendarioFacadeImpl(VacacionesRepository vacacionesRepository,
                                    DiaRepository diaRepository,
                                    DiaLibreRepository diaLibreRepository,
                                    VacacionesDtoFactory vacacionesDtoFactory,
                                    FechaService fechaService) {
        this.vacacionesRepository = vacacionesRepository;
        this.diaRepository = diaRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.vacacionesDtoFactory = vacacionesDtoFactory;
        this.fechaService = fechaService;
    }

    @Override
    public List<VacacionesDto> listVacaciones() {
        LocalDate primerDiaAnyo = fechaService.getStartOfYear();
        return null;
    }

    @Override
    public List<DiaDto> listFestivos() {
        LocalDate primerDiaAnyo = fechaService.getStartOfYear();
        return diaRepository.findAllByFechaGreaterThanEqualAndFinSemanaOrderByIdAsc(primerDiaAnyo).stream()
                .map(d -> new DiaDto(d.getId(), d.getFecha(), d.getDiaSemana()))
                .collect(Collectors.toList());
    }
}
