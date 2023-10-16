package g147bda2023.tpi.services;

import g147bda2023.tpi.models.Estacion;

import java.util.Optional;

public interface EstacionService {
    Optional<Estacion> findById(long id);
}
