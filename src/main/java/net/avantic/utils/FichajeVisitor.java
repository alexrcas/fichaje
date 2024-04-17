package net.avantic.utils;

import net.avantic.domain.model.*;

public interface FichajeVisitor {

    void visit(EntradaJornada entradaJornada);

    void visit(SalidaJornada salidaJornada);

    void visit(EntradaDesayuno entradaDesayuno);

    void visit(SalidaDesayuno salidaDesayuno);

    void visit(EntradaComida entradaComida);

    void visit(SalidaComida salidaComida);
}
