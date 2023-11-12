package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;

public interface AlquilerService {
    Alquiler start(long idCliente, Estacion EstRetiro);
}
