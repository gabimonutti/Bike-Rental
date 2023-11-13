package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import g38.tpi.bda2023.Alquileres.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService{
    private final AlquilerRepository alquilerRepository;

    public Alquiler start(long idCliente, Estacion estRetiro) {
        long id = alquilerRepository.getMaxId() + 1;
        LocalDateTime fechaHoraRetiro = LocalDateTime.now();
        Tarifa tarifa = null;
        Alquiler alquiler = new Alquiler(id, idCliente, 1, estRetiro, null, fechaHoraRetiro, null, null,  tarifa);
        return alquilerRepository.save(alquiler);
    }

    public Alquiler end(long idAlquiler, long idEstacionDevolucion, Estacion estDevolucion) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler not found"));
        alquiler.setEstado(2);
        alquiler.setEstacionDevolucion(estDevolucion);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());
        Tarifa tarifa = chooseTarifa(alquiler);
        alquiler.setTarifa(tarifa);
        BigDecimal monto = calculateMonto(alquiler, tarifa);
        //TODO: return alquilerRepository.save(alquiler);
        return null;
    }

    private Tarifa chooseTarifa(Alquiler alquiler) {
        return null;
    }

    private BigDecimal calculateMonto(Alquiler alquiler, Tarifa tarifa) {
        return null; // TODO: calculate monto
    }
}
