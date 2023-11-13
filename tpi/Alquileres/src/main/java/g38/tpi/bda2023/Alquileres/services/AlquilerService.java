package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;

import java.util.List;

public interface AlquilerService {
    Alquiler start(long idCliente, Estacion EstRetiro);
    Alquiler end(long idAlquiler, Estacion estDevolucion);
    List<Alquiler> findAll();
}
