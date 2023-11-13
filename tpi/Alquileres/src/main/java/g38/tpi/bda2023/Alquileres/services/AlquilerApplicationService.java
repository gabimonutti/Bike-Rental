package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;

public interface AlquilerApplicationService {
    Alquiler start(long idCliente, long idEstRetiro);

    Alquiler end(long idAlquiler, long idEstacionDevolucion);
}
