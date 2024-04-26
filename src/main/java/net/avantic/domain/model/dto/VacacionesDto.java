package net.avantic.domain.model.dto;


public class VacacionesDto {

    private final DiaDto primerDia;
    private final DiaDto diaRegreso;
    private final int numeroDias;
    private final EmpleadoDto empleado;

    public VacacionesDto(DiaDto primerDia, DiaDto diaRegreso, int numeroDias, EmpleadoDto empleado) {
        this.primerDia = primerDia;
        this.diaRegreso = diaRegreso;
        this.numeroDias = numeroDias;
        this.empleado = empleado;
    }

    public DiaDto getPrimerDia() {
        return primerDia;
    }

    public DiaDto getDiaRegreso() {
        return diaRegreso;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public EmpleadoDto getEmpleado() {
        return empleado;
    }
}
