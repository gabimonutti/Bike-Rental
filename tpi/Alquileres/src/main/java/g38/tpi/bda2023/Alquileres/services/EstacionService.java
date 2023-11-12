package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Estacion;

import java.util.Optional;

public interface EstacionService {
    Optional<Estacion> findById(long id);
}
