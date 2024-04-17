package net.avantic.domain.service.impl;

import net.avantic.domain.dao.DiaRepository;
import net.avantic.domain.model.Dia;
import net.avantic.domain.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiaServiceImpl implements DiaService {

    private final DiaRepository diaRepository;

    @Autowired
    public DiaServiceImpl(DiaRepository diaRepository) {
        this.diaRepository = diaRepository;
    }

    @Override
    public Dia getByFecha(LocalDate fecha) {
        return diaRepository.findByFecha(fecha)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado un Dia para la fecha [" + fecha.toString() + "]"));
    }
}
