package g38.tpi.bda2023.Alquileres.application.response;

import g38.tpi.bda2023.Alquileres.models.Tarifa;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarifaSResponse {
    Long id;
    Integer tipoTarifa;
    String definicion;
    Integer diaSemana;
    BigDecimal montoFijoAlquiler;
    BigDecimal montoMinutoFraccion;
    BigDecimal montoHora;
    BigDecimal montoKm;

    public static TarifaSResponse from(Tarifa tarifa) {
        return TarifaSResponse.builder()
                .id(tarifa.getId())
                .tipoTarifa(tarifa.getTipoTarifa())
                .definicion(tarifa.getDefinicion())
                .diaSemana(tarifa.getDiaSemana())
                .montoFijoAlquiler(tarifa.getMontoFijoAlquiler())
                .montoMinutoFraccion(tarifa.getMontoMinutoFraccion())
                .montoHora(tarifa.getMontoHora())
                .montoKm(tarifa.getMontoKm())
                .build();
    }
}
