package g38.tpi.bda2023.Alquileres.application.response;

import g38.tpi.bda2023.Alquileres.models.Estacion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstacionResponse {
    Long id;
    String nombre;
    LocalDateTime fechaHoraCreacion;
    double latitud;
    double longitud;

    public static EstacionResponse from(Estacion estacion) {
        if(estacion == null) {
            return null;
        }
        return EstacionResponse.builder()
                .id(estacion.getId())
                .nombre(estacion.getNombre())
                .fechaHoraCreacion(estacion.getFechaHoraCreacion())
                .latitud(estacion.getLatitud())
                .longitud(estacion.getLongitud())
                .build();
    }
}

