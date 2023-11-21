package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    Alquiler start(long idCliente, Long idEstRetiro);
    Alquiler end(Alquiler alquiler, Long estDevolucion);
    Optional<Alquiler> findById(long id);
    List<Alquiler> findAll();
    List<Alquiler> findAllByMontoGreaterThan(BigDecimal monto);
    List<Alquiler> findAllByIdCliente(int idCliente);
    List<Alquiler> getAllByIdClienteAndMontoGreaterThan(int idCliente, BigDecimal monto);
}
