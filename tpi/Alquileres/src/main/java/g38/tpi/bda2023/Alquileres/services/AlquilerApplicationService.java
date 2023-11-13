package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.models.Alquiler;

public interface AlquilerApplicationService {
    AlquilerResponse start(long idCliente, long idEstRetiro);

    AlquilerResponse end(long idAlquiler, long idEstacionDevolucion, String currency);
}
