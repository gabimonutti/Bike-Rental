package g38.tpi.bda2023.Alquileres.application.response;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlquilerResponse {
    private Long id;
    private Long idCliente;
    private int estado;
    private Estacion estacionRetiro; //TODO: hacer que devuelva estacionResponse
    private Estacion estacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private BigDecimal monto;
    private Tarifa tarifa;

    public static AlquilerResponse from (Alquiler alquiler) {
        return AlquilerResponse.builder()
                .id(alquiler.getId())
                .idCliente(alquiler.getIdCliente())
                .estado(alquiler.getEstado())
                .estacionRetiro(alquiler.getEstacionRetiro())
                .estacionDevolucion(alquiler.getEstacionDevolucion())
                .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                .monto(alquiler.getMonto())
                .tarifa(alquiler.getTarifa())
                .build();
    }
}
