package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.application.response.InicioAlquilerResponse;
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
    @Override public InicioAlquilerResponse start(long idCliente, long idEstRetiro) {
        Estacion estRetiro = estacionService.findById(idEstRetiro)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found"));

        return InicioAlquilerResponse.from(alquilerService.start(idCliente, estRetiro));
    }

    @Override public AlquilerResponse end(long idAlquiler, long idEstacionDevolucion, String moneda) {
        Estacion estDevolucion = estacionService.findById(idEstacionDevolucion)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Devolucion Not Found"));

        if (!(exchangeService.ExchangesAvailable.contains(moneda))) throw new IllegalArgumentException("Exchange not allowed");

        Alquiler alquiler = alquilerService.end(idAlquiler, estDevolucion);
        AlquilerResponse alquilerResponse = AlquilerResponse.from(alquiler);

        if (exchangeService.ExchangesAvailable.contains(moneda)) {
            double montoExchanged = exchangeService.getMonto(moneda, alquiler.getMonto().doubleValue());
            alquilerResponse.setMonto(String.format("%.02f", montoExchanged));
        }
        return alquilerResponse;
    }
}
