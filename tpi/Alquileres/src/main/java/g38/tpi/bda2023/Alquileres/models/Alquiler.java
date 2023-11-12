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
    private Long id;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    private int estado;

    @ManyToOne
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionRetiro;

    @ManyToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION")
    private Estacion estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa tarifa;
}
