package net.avantic.utils;

import net.avantic.domain.model.EntradaJornada;

public interface FichajeVisitor {

    void visit(EntradaJornada entradaJornada);
}
