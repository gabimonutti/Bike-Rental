package g38.tpi.bda2023.Alquileres.application.response;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InicioAlquilerResponse {
    private Long id;
    private Long idCliente;
    private int estado;
    private Long idEstacionRetiro;
    private LocalDateTime fechaHoraRetiro;

    public static InicioAlquilerResponse from (Alquiler alquiler) {
        return InicioAlquilerResponse.builder()
                .id(alquiler.getId())
                .idCliente(alquiler.getIdCliente())
                .estado(alquiler.getEstado())
                .idEstacionRetiro(alquiler.getIdEstacionRet())
                .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                .build();
    }
}
