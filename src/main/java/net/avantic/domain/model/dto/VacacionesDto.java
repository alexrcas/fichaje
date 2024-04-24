package net.avantic.domain.model.dto;


public class VacacionesDto {

    private final DiaDto primerDia;
    private final DiaDto ultimoDia;
    private final EmpleadoDto empleadoDto;

    public VacacionesDto(DiaDto primerDia, DiaDto ultimoDia, EmpleadoDto empleadoDto) {
        this.primerDia = primerDia;
        this.ultimoDia = ultimoDia;
        this.empleadoDto = empleadoDto;
    }

    public DiaDto getPrimerDia() {
        return primerDia;
    }

    public DiaDto getUltimoDia() {
        return ultimoDia;
    }

    public EmpleadoDto getEmpleadoDto() {
        return empleadoDto;
    }
}
