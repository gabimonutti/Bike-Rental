package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Tarifa;
import g38.tpi.bda2023.Alquileres.repositories.TarifaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {
    TarifaRepository tarifaRepository;

    @Override public Optional<Tarifa> findTarifaFecha(char definicion, int diaMes, int mes, int anio) {
        return tarifaRepository.findByDefinicionAndDiaMesAndMesAndAnio(definicion, diaMes, mes, anio);
    }

    @Override public Optional<Tarifa> findTarifaDiaSemana(int diaSemana) {
        return tarifaRepository.findByDiaSemana(diaSemana);
    }
}
