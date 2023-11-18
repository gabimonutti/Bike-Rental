package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.application.response.InicioAlquilerResponse;
import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

        if (!(moneda == null || moneda.trim().isEmpty()) && !Currency.isValid(moneda)) throw new IllegalArgumentException("Exchange not allowed");

        Alquiler alquiler = alquilerService.end(idAlquiler, estDevolucion);
        AlquilerResponse alquilerResponse = AlquilerResponse.from(alquiler);

        if (Currency.isValid(moneda)) {
            double montoExchanged = exchangeService.getMonto(moneda, alquiler.getMonto().doubleValue());
            alquilerResponse.setMonto(String.format("%.02f", montoExchanged));
        }
        return alquilerResponse;
    }
    public List<AlquilerResponse> findAllAlquileres(){

        return alquilerService.findAll()
                .stream()
                .map(AlquilerResponse::from)
                .toList();
    }

    public List<AlquilerResponse> findByIdCliente(int idCliente){
        return alquilerService.findAllByIdCliente(idCliente)
                .stream()
                .map(AlquilerResponse::from)
                .toList();
    }

    public List<AlquilerResponse> findByMontoGtThan(BigDecimal monto){
        return alquilerService.findAllByMontoGreaterThan(monto)
                .stream()
                .map(AlquilerResponse::from)
                .toList();
    }

    public List<AlquilerResponse> getAllByIdClienteAndMontoGreaterThan(int idCliente, BigDecimal monto){
        return alquilerService.getAllByIdClienteAndMontoGreaterThan(idCliente, monto)
                .stream()
                .map(AlquilerResponse::from)
                .toList();
    }




}

