package g38.tpi.bda2023.Estaciones.services;

import g38.tpi.bda2023.Estaciones.models.Estacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EstacionService {
    List<Estacion> findAll();

    Optional<Estacion> findById(Long id);

    Estacion create (String nombre, double latitud, double longitud);
}
