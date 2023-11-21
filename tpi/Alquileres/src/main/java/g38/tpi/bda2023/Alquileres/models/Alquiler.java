package g38.tpi.bda2023.Alquileres.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity(name = "ALQUILERES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Alquiler {
    @Id
    Long id;

    @Column(name = "ID_CLIENTE")
    Long idCliente;

    int estado;

    @Column(name = "ESTACION_RETIRO")
    Long idEstacionRet;

    @Column(name = "ESTACION_DEVOLUCION")
    Long idEstacionDev;

    @Column(name = "FECHA_HORA_RETIRO", nullable = true)
    LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION", nullable = true)
    LocalDateTime fechaHoraDevolucion;

    BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA", nullable = true)
    Tarifa tarifa;

    public Alquiler(Long id, Long idCliente, Long idEstacionRetiro) {
        this.id = id;
        this.idCliente = idCliente;
        this.estado = 1;
        this.idEstacionRet = idEstacionRetiro;
        this.fechaHoraRetiro = LocalDateTime.now();;
    }

    public boolean isFinished() { return estado == 2; }

    public void end(Long idEstacionDev) {
        this.idEstacionDev = idEstacionDev;
        this.estado = 2;
        this.fechaHoraDevolucion = LocalDateTime.now();
    }

    public BigDecimal calculateMonto(Alquiler alquiler, Tarifa tarifa, double distanciaKm) {
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
            throw new IllegalArgumentException("FechaHoraDevolucion is too far from FechaHoraRetiro");
        }

        if(minutes <= 31) {
            monto = monto.add(tarifa.getMontoMinutoFraccion().multiply(BigDecimal.valueOf((minutes))));
        }
        else {
            horas++;
        }
        monto = monto.add(tarifa.getMontoHora().multiply(BigDecimal.valueOf(horas)));

        monto = monto.add(tarifa.getMontoKm().multiply(BigDecimal.valueOf((distanciaKm))));
        return monto;
    }
}
