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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionRetiro;

    @ManyToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION", nullable = true)
    private Estacion estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO", nullable = true)
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION", nullable = true)
    private LocalDateTime fechaHoraDevolucion;

    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA", nullable = true)
    private Tarifa tarifa;

    public Alquiler(Long id, Long idCliente, int estado, Estacion estacionRetiro, LocalDateTime fechaHoraRetiro) {
        this.id = id;
        this.idCliente = idCliente;
        this.estado = estado;
        this.estacionRetiro = estacionRetiro;
        this.fechaHoraRetiro = fechaHoraRetiro;
    }
}
