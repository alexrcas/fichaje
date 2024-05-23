package net.avantic.story.web.listFestivos;

import net.avantic.domain.dao.*;
import net.avantic.domain.model.Dia;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.FestivoDto;
import net.avantic.domain.service.FechaService;
import net.avantic.domain.service.SecurityUtilsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListFestivosFacadeImpl implements ListFestivosFacade {

    private final FechaService fechaService;
    private final FestivoRepository festivoRepository;
    private final DiaRepository diaRepository;
    private final SecurityUtilsService securityUtilsService;

    public ListFestivosFacadeImpl(FechaService fechaService,
                                  FestivoRepository festivoRepository,
                                  DiaRepository diaRepository,
                                  SecurityUtilsService securityUtilsService) {
        this.fechaService = fechaService;
        this.festivoRepository = festivoRepository;
        this.diaRepository = diaRepository;
        this.securityUtilsService = securityUtilsService;
    }


    @Override
    public List<FestivoDto> listFestivos() {
        return diaRepository.findAllByFechaBetweenThanEqualAndNotFinSemanaAndFestivoOrderByIdAsc(fechaService.getStartOfYear(), fechaService.getEndOfYear()).stream()
                .map(festivoRepository::findByDia)
                .map(festivo -> {
                    String motivo = festivo.get().getMotivo();
                    Dia dia = festivo.get().getDia();
                    DiaDto diaDto = new DiaDto(dia.getId(), dia.getFecha(), dia.getDiaSemana());
                    return new FestivoDto(dia.getId(), diaDto, motivo);
                })
                .toList();
    }

    @Override
    public String getAuthenticatedUsername() {
        return securityUtilsService.getAuthenticatedUser().getEmail();
    }


}
