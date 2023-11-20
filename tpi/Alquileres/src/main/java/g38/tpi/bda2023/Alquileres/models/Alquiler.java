package g38.tpi.bda2023.Alquileres.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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

    @Transient
    Estacion estacionRetiro;

    @Transient
    Estacion estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO", nullable = true)
    LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION", nullable = true)
    LocalDateTime fechaHoraDevolucion;

    BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA", nullable = true)
    Tarifa tarifa;

    public Alquiler(Long id, Long idCliente, Estacion estacionRetiro) {
        this.id = id;
        this.idCliente = idCliente;
        this.estado = 1;
        this.estacionRetiro = estacionRetiro;
        this.idEstacionRet = estacionRetiro.getId();
        this.fechaHoraRetiro = LocalDateTime.now();;
    }
}
