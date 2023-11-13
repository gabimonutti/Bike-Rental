package g38.tpi.bda2023.Alquileres.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity(name = "TARIFAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tarifa {
    @Id
    Long id;

    @Column(name = "TIPO_TARIFA")
    Integer tipoTarifa;

    @Column(name = "DEFINICION")
    String definicion;

    @Column(name = "DIA_SEMANA")
    Integer diaSemana;

    @Column(name = "DIA_MES")
    Integer diaMes;

    Integer mes;

    Integer anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    BigDecimal montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    BigDecimal montoMinutoFraccion;

    @Column(name = "MONTO_HORA")
    BigDecimal montoHora;

    @Column(name = "MONTO_KM")
    BigDecimal montoKm;
}
