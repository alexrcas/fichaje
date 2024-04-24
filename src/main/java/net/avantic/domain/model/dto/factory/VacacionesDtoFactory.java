package net.avantic.domain.model.dto.factory;

import net.avantic.domain.model.Dia;
import net.avantic.domain.model.DiaLibre;
import net.avantic.domain.model.Empleado;
import net.avantic.domain.model.dto.DiaDto;
import net.avantic.domain.model.dto.EmpleadoDto;
import net.avantic.domain.model.dto.VacacionesDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacacionesDtoFactory {

    public VacacionesDto newDto(List<DiaLibre> diasLibres) {
        Empleado empleado = diasLibres.get(0).getEmpleado();
        Dia primerDia = diasLibres.get(0).getDia();
        Dia ultimoDia = diasLibres.get(diasLibres.size() - 1).getDia();

        EmpleadoDto empleadoDto = new EmpleadoDto(empleado.getId(), empleado.getEmail());
        DiaDto primerDiaDto = new DiaDto(primerDia.getId(), primerDia.getFecha(), primerDia.getDiaSemana());
        DiaDto ultimoDiaDto = new DiaDto(ultimoDia.getId(), ultimoDia.getFecha(), ultimoDia.getDiaSemana());

        return new VacacionesDto(primerDiaDto, ultimoDiaDto, empleadoDto);
    }
}
