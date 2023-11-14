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
    private Long id;
    private Integer tipoTarifa;
    private String definicion;
    private Integer diaMes;
    private Integer mes;
    private Integer anio;
    private BigDecimal montoFijoAlquiler;
    private BigDecimal montoMinutoFraccion;
    private BigDecimal montoHora;
    private BigDecimal montoKm;

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
