package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import g38.tpi.bda2023.Alquileres.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
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

    @Override public Alquiler start(long idCliente, Estacion estRetiro) {
        long id = alquilerRepository.getMaxId() + 1;
        LocalDateTime fechaHoraRetiro = LocalDateTime.now();
        Alquiler alquiler = new Alquiler(id, idCliente, 1, estRetiro, fechaHoraRetiro);
        return alquilerRepository.save(alquiler);
    }

    @Override public Alquiler end(long idAlquiler, Estacion estDevolucion) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler not found"));
        if(alquiler.getEstado() == 2) { throw new IllegalArgumentException("Alquiler already finished"); }

        alquiler.setEstado(2);
        alquiler.setEstacionDevolucion(estDevolucion);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());

        Tarifa tarifa = chooseTarifa(alquiler);
        alquiler.setTarifa(tarifa);
        BigDecimal monto = calculateMonto(alquiler, tarifa);
        alquiler.setMonto(monto);
        return alquilerRepository.save(alquiler);
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

    private BigDecimal calculateMonto(Alquiler alquiler, Tarifa tarifa) {
        BigDecimal monto = BigDecimal.valueOf((0));
        monto = monto.add(tarifa.getMontoFijoAlquiler());
        Duration duration = Duration.between(alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion());
        long horas;
        long minutes;
        try {
            horas = duration.toHours();
            minutes = duration.toMinutes() - horas*60;
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("FechaHoraDevolucion is too far form FechaHoraRetiro");
        }

        if(minutes <= 31) {
            monto = monto.add(tarifa.getMontoMinutoFraccion().multiply(BigDecimal.valueOf((minutes))));
        }
        else {
            horas++;
        }
        monto = monto.add(tarifa.getMontoHora().multiply(BigDecimal.valueOf(horas)));

        double distanciaKm = distanciaService.calcularDistancia(alquiler.getEstacionDevolucion().getLatitud(),
                alquiler.getEstacionDevolucion().getLongitud(), alquiler.getEstacionRetiro().getLatitud(),
                alquiler.getEstacionRetiro().getLongitud()) / 1000;
        monto = monto.add(tarifa.getMontoKm().multiply(BigDecimal.valueOf((distanciaKm))));
        return monto;
    }
    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public List<Alquiler> findAllByEstadoAndMontoGreaterThan(int estado, BigDecimal monto) {
        return alquilerRepository.findAllByEstadoAndMontoGreaterThan(estado, monto);
    }

    @Override
    public List<Alquiler> findAllByEstado(int estado) {
        return alquilerRepository.findAllByEstado(estado);
    }

    @Override
    public List<Alquiler> findAllByMontoGreaterThan(BigDecimal monto) {
        return alquilerRepository.findAllByMontoGreaterThan(monto);
    }
}
