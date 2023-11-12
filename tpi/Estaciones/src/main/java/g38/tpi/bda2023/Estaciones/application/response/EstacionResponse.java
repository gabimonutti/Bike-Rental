package g38.tpi.bda2023.Estaciones.application.response;

import g38.tpi.bda2023.Estaciones.models.Estacion;
import jakarta.persistence.Column;
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
        return EstacionResponse.builder()
                .id(estacion.getId())
                .nombre(estacion.getNombre())
                .fechaHoraCreacion(estacion.getFechaHoraCreacion())
                .latitud(estacion.getLatitud())
                .longitud(estacion.getLongitud())
                .build();
    }
}
