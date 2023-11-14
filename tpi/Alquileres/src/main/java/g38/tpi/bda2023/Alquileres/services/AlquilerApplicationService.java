package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.application.response.InicioAlquilerResponse;
import g38.tpi.bda2023.Alquileres.models.Alquiler;

import java.math.BigDecimal;
import java.util.List;

public interface AlquilerApplicationService {
    InicioAlquilerResponse start(long idCliente, long idEstRetiro);

    AlquilerResponse end(long idAlquiler, long idEstacionDevolucion, String currency);

    List<AlquilerResponse> findAllAlquileres();

    List<AlquilerResponse> findByEstado(int estado);

    List<AlquilerResponse> findByMontoGtThan(BigDecimal monto);

    List<AlquilerResponse> findByEstadoAndMontoGtThan(int estado, BigDecimal monto);
}
