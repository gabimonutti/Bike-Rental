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
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlquilerResponse {
    private Long id;
    private Long idCliente;
    private int estado;
    private Long idEstacionRetiro;
    private Long idEstacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private String monto;
    private Optional<Object> tarifa;

    public static AlquilerResponse from (Alquiler alquiler) {
        return AlquilerResponse.builder()
                .id(alquiler.getId())
                .idCliente(alquiler.getIdCliente())
                .estado(alquiler.getEstado())
                .idEstacionRetiro(alquiler.getIdEstacionRet())
                .idEstacionDevolucion(alquiler.getIdEstacionDev())
                .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                //fecha hora Devolucion puede ser null
                .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                // devolvemos el monto en string por precision de centavos y redondeamos a 2 digitos
                .monto((alquiler.getMonto() == null)?null:String.format("%.02f", alquiler.getMonto().floatValue()))
                .tarifa(alquiler.getTarifa() != null ?
                        Optional.of(Objects.equals(alquiler.getTarifa().getDefinicion(), "S") ?
                                TarifaSResponse.from(alquiler.getTarifa()) :
                                TarifaCResponse.from(alquiler.getTarifa())) :
                        Optional.empty())
                .build();
    }
}