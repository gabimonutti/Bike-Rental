package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlquilerApplicationServiceImpl implements AlquilerApplicationService {
    private final AlquilerService alquilerService;
    private final EstacionService estacionService;
    private final ExchangeService exchangeService;
    @Override public AlquilerResponse start(long idCliente, long idEstRetiro) {
        Estacion estRetiro = estacionService.findById(idEstRetiro)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found"));

        return AlquilerResponse.from(alquilerService.start(idCliente, estRetiro));
    }

    @Override public AlquilerResponse end(long idAlquiler, long idEstacionDevolucion, String moneda) {
        Estacion estDevolucion = estacionService.findById(idEstacionDevolucion)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Devolucion Not Found"));
        Alquiler alquiler = alquilerService.end(idAlquiler, estDevolucion);
        AlquilerResponse response = AlquilerResponse.from(alquiler);
        if (moneda != null) {
            String montoExchanged = String.format("%.02f", exchangeService.getMonto(moneda, alquiler.getMonto().doubleValue()));
            response.setMonto(montoExchanged);
        }
        // TODO: Validar tipo de moneda
        return response;
    }
}
