package net.avantic.story.web.listFestivos;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.FestivoDto;
import net.avantic.domain.service.DiaService;
import net.avantic.domain.service.FechaService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListFestivosFacadeImpl implements ListFestivosFacade {

    private final EmpleadoRepository empleadoRepository;
    private final DiaService diaService;
    private final FechaService fechaService;
    private final VacacionesRepository vacacionesRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final FestivoRepository festivoRepository;
    private final DiaRepository diaRepository;

    public ListFestivosFacadeImpl(EmpleadoRepository empleadoRepository,
                                  DiaService diaService,
                                  FechaService fechaService,
                                  VacacionesRepository vacacionesRepository,
                                  DiaLibreRepository diaLibreRepository,
                                  FestivoRepository festivoRepository,
                                  DiaRepository diaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.diaService = diaService;
        this.fechaService = fechaService;
        this.vacacionesRepository = vacacionesRepository;
        this.diaLibreRepository = diaLibreRepository;
        this.festivoRepository = festivoRepository;
        this.diaRepository = diaRepository;
    }


    @Override
    public List<FestivoDto> listFestivos() {
        return diaRepository.findAllByFechaGreaterThanEqualAndNotFinSemanaAndFestivoOrderByIdAsc(fechaService.getStartOfYear()).stream()
                .map(festivoRepository::findByDia)
                .map(festivo -> {
                    String motivo = festivo.get().getMotivo();
                    Dia dia = festivo.get().getDia();
                    DiaDto diaDto = new DiaDto(dia.getId(), dia.getFecha(), dia.getDiaSemana());
                    return new FestivoDto(dia.getId(), diaDto, motivo);
                })
                .toList();
    }


}
