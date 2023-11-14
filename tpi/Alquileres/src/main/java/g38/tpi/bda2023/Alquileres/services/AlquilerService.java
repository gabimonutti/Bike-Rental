package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;

import java.math.BigDecimal;
import java.util.List;

public interface AlquilerService {
    Alquiler start(long idCliente, Estacion EstRetiro);
    Alquiler end(long idAlquiler, Estacion estDevolucion);
    List<Alquiler> findAll();
    List<Alquiler> findAllByMontoGreaterThan(BigDecimal monto);
    List<Alquiler> findAllByIdCliente(int idCliente);
    List<Alquiler> getAllByIdClienteAndMontoGreaterThan(int idCliente, BigDecimal monto);
}
