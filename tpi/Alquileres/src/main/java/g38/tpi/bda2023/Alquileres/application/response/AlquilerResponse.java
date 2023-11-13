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
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlquilerResponse {
    private Long id;
    private Long idCliente;
    private int estado;
    private EstacionResponse estacionRetiro;
    private EstacionResponse estacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private String monto;
    private Object tarifa;

    public static AlquilerResponse from (Alquiler alquiler) {
        return AlquilerResponse.builder()
                .id(alquiler.getId())
                .idCliente(alquiler.getIdCliente())
                .estado(alquiler.getEstado())
                .estacionRetiro(EstacionResponse.from(alquiler.getEstacionRetiro()))
                .estacionDevolucion(EstacionResponse.from(alquiler.getEstacionDevolucion()))
                .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                // devolvemos el monto en string por precision de centavos y redondeamos a 2 digitos
                .monto((alquiler.getMonto() == null)?null:String.format("%.02f", alquiler.getMonto().floatValue()))
                .tarifa(Objects.equals(alquiler.getTarifa().getDefinicion(), "S") ?
                        TarifaSResponse.from(alquiler.getTarifa()) :
                        TarifaCResponse.from(alquiler.getTarifa()))
                .build();
    }
}
