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
        estacionService.findById(idEstRetiro)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found"));

        if (idCliente < 0) { throw new IllegalArgumentException("IdCliente must be equal or higher than 0"); };

        return InicioAlquilerResponse.from(alquilerService.start(idCliente, idEstRetiro));
    }

    @Override public AlquilerResponse end(long idAlquiler, long idEstacionDevolucion, String moneda) {
        estacionService.findById(idEstacionDevolucion)
                .orElseThrow(() -> new IllegalArgumentException("Estacion Devolucion Not Found"));

        if (!(moneda == null || moneda.trim().isEmpty()) && !Currency.isValid(moneda)) throw new IllegalArgumentException("Exchange not allowed");

        Alquiler alquiler = alquilerService.findById(idAlquiler)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler not found"));

        alquilerService.end(alquiler, idEstacionDevolucion);

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

//    private void getEstaciones(Alquiler alquiler) {
//        if (alquiler.getIdEstacionRet() != null) {
//            alquiler.setEstacionRetiro(estacionService.findById(alquiler.getIdEstacionRet())
//                    .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found")));
//        }
//        if (alquiler.getIdEstacionDev() != null) {
//            alquiler.setEstacionDevolucion(estacionService.findById(alquiler.getIdEstacionDev())
//                    .orElseThrow(() -> new IllegalArgumentException("Estacion Devolucion Not Found")));
//        }
//    }




}

