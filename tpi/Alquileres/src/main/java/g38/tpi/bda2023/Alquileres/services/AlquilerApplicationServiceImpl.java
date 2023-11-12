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
    public Alquiler start(long idCliente, long idEstRetiro) {
        Estacion estRetiro = estacionService.findById(idEstRetiro)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found"));

        return alquilerService.start(idCliente, estRetiro);
    }
}
