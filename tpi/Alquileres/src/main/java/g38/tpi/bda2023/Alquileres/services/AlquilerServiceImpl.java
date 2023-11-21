package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import g38.tpi.bda2023.Alquileres.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService{
    private final AlquilerRepository alquilerRepository;
    private final TarifaService tarifaService;
    private final DistanciaService distanciaService;
    @Autowired
    private final EstacionService estacionService;

    @Override public Alquiler start(long idCliente, Long idEstRetiro) {
        long id = alquilerRepository.getMaxId() + 1;
        Alquiler alquiler = new Alquiler(id, idCliente, idEstRetiro);
        alquilerRepository.save(alquiler);
        return alquiler;
    }

    @Override public Alquiler end(Alquiler alquiler, Long idEstDevolucion) {
        if(alquiler.isFinished()) { throw new IllegalArgumentException("Alquiler already finished"); }

        alquiler.end(idEstDevolucion);
        Tarifa tarifa = chooseTarifa(alquiler);
        alquiler.setTarifa(tarifa);

        double distanciaKm = calcularDistancia(alquiler);

        BigDecimal monto = alquiler.calculateMonto(alquiler, tarifa, distanciaKm);
        alquiler.setMonto(monto);
        alquilerRepository.save(alquiler);
        return alquiler;
    }

    private Tarifa chooseTarifa(Alquiler alquiler) {
        int diaMes = alquiler.getFechaHoraRetiro().getDayOfMonth();
        int mes = alquiler.getFechaHoraRetiro().getMonthValue();
        int anio = alquiler.getFechaHoraRetiro().getYear();
        Optional<Tarifa> tarifa = tarifaService.findTarifaFecha("C", diaMes, mes, anio);

        if(tarifa.isEmpty()) {
            int diaSemana = alquiler.getFechaHoraDevolucion().getDayOfWeek().getValue();
            tarifa = tarifaService.findTarifaDiaSemana(diaSemana);
        }

        if(tarifa.isEmpty()) { throw new IllegalArgumentException("Tarifa not found"); }
        return tarifa.get();
    }

    private double calcularDistancia(Alquiler alquiler) {
        Estacion estacionRetiro = estacionService.findById(alquiler.getIdEstacionRet())
                .orElseThrow(() -> new IllegalArgumentException("Estacion Retiro Not Found"));
        Estacion estacionDevolucion = estacionService.findById(alquiler.getIdEstacionDev())
                .orElseThrow(() -> new IllegalArgumentException("Estacion Devolucion Not Found"));

        double distanciaKm = distanciaService.calcularDistancia(estacionRetiro.getLatitud(),
                estacionRetiro.getLongitud(), estacionDevolucion.getLatitud(),
                estacionDevolucion.getLongitud()) / 1000;
        return distanciaKm;
    }

    @Override
    public Optional<Alquiler> findById(long id) { return alquilerRepository.findById(id); }

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public List<Alquiler> getAllByIdClienteAndMontoGreaterThan(int idCliente, BigDecimal monto) {
        if(idCliente < 1) { throw new IllegalArgumentException("IdCliente should be positive"); }
        if(monto.doubleValue() < 0) { throw new IllegalArgumentException("Monto should be positive or zero"); }
        return alquilerRepository.getAllByIdClienteAndMontoGreaterThan(idCliente, monto);
    }

    @Override
    public List<Alquiler> findAllByIdCliente(int idCliente) {
        if(idCliente < 1) { throw new IllegalArgumentException("IdCliente should be positive"); }
        return alquilerRepository.findAllByIdCliente(idCliente);
    }

    @Override
    public List<Alquiler> findAllByMontoGreaterThan(BigDecimal monto) {
        if(monto.doubleValue() < 0) { throw new IllegalArgumentException("Monto should be positive or zero"); }
        return alquilerRepository.findAllByMontoGreaterThan(monto);
    }
}
