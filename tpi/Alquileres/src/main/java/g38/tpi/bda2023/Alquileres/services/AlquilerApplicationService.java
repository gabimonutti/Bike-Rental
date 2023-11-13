package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.application.response.InicioAlquilerResponse;
import g38.tpi.bda2023.Alquileres.models.Alquiler;

public interface AlquilerApplicationService {
    InicioAlquilerResponse start(long idCliente, long idEstRetiro);

    AlquilerResponse end(long idAlquiler, long idEstacionDevolucion, String currency);
}
