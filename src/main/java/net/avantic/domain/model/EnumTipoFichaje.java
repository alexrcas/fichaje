package net.avantic.domain.model;

public enum EnumTipoFichaje {

    ENTRADA_JORNADA("Entrada Jornada"),
    SALIDA_JORNADA("Salida Jornada"),

    ENTRADA_DESAYUNO("Entrada Desayuno"),
    SALIDA_DESAYUNO("Salida Desayuno"),

    ENTRADA_COMIDA("Entrada Comida"),
    SALIDA_COMIDA("Salida Comida"),

    ENTRADA_OTROS("Entrada Otros"),
    SALIDA_OTROS("Salida Otros");

    private String name;

    EnumTipoFichaje(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
