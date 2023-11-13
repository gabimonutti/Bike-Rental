package g38.tpi.bda2023.Alquileres.application.response;

import g38.tpi.bda2023.Alquileres.models.Tarifa;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarifaCResponse {
    Long id;
    Integer tipoTarifa;
    String definicion;
    Integer diaMes;
    Integer mes;
    Integer anio;
    BigDecimal montoFijoAlquiler;
    BigDecimal montoMinutoFraccion;
    BigDecimal montoHora;
    BigDecimal montoKm;

    public static TarifaCResponse from(Tarifa tarifa) {
        return TarifaCResponse.builder()
                .id(tarifa.getId())
                .tipoTarifa(tarifa.getTipoTarifa())
                .definicion(tarifa.getDefinicion())
                .diaMes(tarifa.getDiaMes())
                .mes(tarifa.getMes())
                .anio(tarifa.getAnio())
                .montoFijoAlquiler(tarifa.getMontoFijoAlquiler())
                .montoMinutoFraccion(tarifa.getMontoMinutoFraccion())
                .montoHora(tarifa.getMontoHora())
                .montoKm(tarifa.getMontoKm())
                .build();
    }
}
