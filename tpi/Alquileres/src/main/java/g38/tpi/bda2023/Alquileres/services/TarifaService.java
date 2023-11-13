package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Tarifa;

import java.util.Optional;

public interface TarifaService {
    Optional<Tarifa> findTarifaFecha(String definicion, int diaMes, int mes, int anio);

    Optional<Tarifa> findTarifaDiaSemana(int diaSemana);
}
