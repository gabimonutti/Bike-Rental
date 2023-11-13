package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlquilerApplicationServiceImpl implements AlquilerApplicationService {
    private final AlquilerService alquilerService;
    private final EstacionService estacionService;
    @Override public Alquiler start(long idCliente, long idEstRetiro) {
        Estacion estRetiro = estacionService.findById(idEstRetiro)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found"));

        return alquilerService.start(idCliente, estRetiro);
    }

    @Override public Alquiler end(long idAlquiler, long idEstacionDevolucion) {
        Estacion estDevolucion = estacionService.findById(idEstacionDevolucion)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Devolucion Not Found"));
        return alquilerService.end(idAlquiler, estDevolucion);
    }
}
